'use strict';

var React = require('react-native');
var {
  Dimensions,
  Text,
  View,
  TouchableOpacity,
  PanResponder,
  Animated,
} = React;

var DefaultTabBar = require('./DefaultTabBar');
var deviceWidth = Dimensions.get('window').width;

var ScrollableTabView = React.createClass({
  getDefaultProps() {
    return {
      edgeHitWidth: 30,
    }
  },

  getInitialState() {
    return { currentPage: 0, scrollValue: new Animated.Value(0) };
  },

  componentWillMount() {
    var release = (e, gestureState) => {
      var relativeGestureDistance = gestureState.dx / deviceWidth,
          lastPageIndex = this.props.children.length - 1,
          vx = gestureState.vx,
          newPage = this.state.currentPage;

      if (relativeGestureDistance < -0.5 || (relativeGestureDistance < 0 && vx <= 0.5)) {
        newPage = newPage + 1;
      } else if (relativeGestureDistance > 0.5 || (relativeGestureDistance > 0 && vx >= 0.5)) {
        newPage = newPage - 1;
      }

      this.props.hasTouch && this.props.hasTouch(false);
      this.goToPage(Math.max(0, Math.min(newPage, this.props.children.length - 1)));
    }

    this._panResponder = PanResponder.create({
      // Claim responder if it's a horizontal pan
      onMoveShouldSetPanResponder: (e, gestureState) => {
        if (Math.abs(gestureState.dx) > Math.abs(gestureState.dy)) {
          if ((gestureState.moveX <= this.props.edgeHitWidth ||
              gestureState.moveX >= deviceWidth - this.props.edgeHitWidth) &&
                this.props.locked !== true) {
            this.props.hasTouch && this.props.hasTouch(true);
            return true;
          }
        }
      },

      // Touch is released, scroll to the one that you're closest to
      onPanResponderRelease: release,
      onPanResponderTerminate: release,

      // Dragging, move the view with the touch
      onPanResponderMove: (e, gestureState) => {
        var dx = gestureState.dx;
        var lastPageIndex = this.props.children.length - 1;

        // This is awkward because when we are scrolling we are offsetting the underlying view
        // to the left (-x)
        var offsetX = dx - (this.state.currentPage * deviceWidth);
        this.state.scrollValue.setValue(-1 * offsetX / deviceWidth);
      },
    });
  },

  goToPage(pageNumber) {
    this.props.onChangeTab && this.props.onChangeTab({
      i: pageNumber, ref: this.props.children[pageNumber]
    });

    this.setState({
      currentPage: pageNumber
    });

    Animated.spring(this.state.scrollValue, {toValue: pageNumber, friction: 10, tension: 50}).start();
  },

  renderTabBar(props) {
    if (this.props.renderTabBar === false) {
      return null;
    } else if (this.props.renderTabBar) {
      return React.cloneElement(this.props.renderTabBar(), props);
    } else {
      return <DefaultTabBar {...props} />;
    }
  },

  render() {
    var sceneContainerStyle = {
      width: deviceWidth * this.props.children.length,
      flex: 1,
      flexDirection: 'row',
    };

    var translateX = this.state.scrollValue.interpolate({
      inputRange: [0, 1], outputRange: [0, -deviceWidth]
    });

    return (
      <View style={{flex: 1}}>
        {this.props.topBar && this.renderTabBar({goToPage: this.goToPage,
                            tabs: this.props.children.map((child) => child.props.tabLabel),
                            activeTab: this.state.currentPage,
                            scrollValue: this.state.scrollValue})}
        <Animated.View style={[sceneContainerStyle, {transform: [{translateX}]}]}
          {...this._panResponder.panHandlers}>
          {this.props.children}
        </Animated.View>
        {!this.props.topBar && this.renderTabBar({goToPage: this.goToPage,
                            tabs: this.props.children.map((child) => child.props.tabLabel),
                            activeTab: this.state.currentPage,
                            scrollValue: this.state.scrollValue})}
      </View>
    );
  }
});

module.exports = ScrollableTabView;

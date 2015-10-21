package sri.reactive2015.components.schedule

import sri.core._
import sri.mobile.all._
import sri.reactive2015.utils.{Colors, Device}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object ScheduleTabBar {


  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = {
      val numberOfTabs = tabOptions.length
      js.Dynamic.global.sv = propsDynamic.scrollValue
      val left = propsDynamic.scrollValue.interpolate(
        json(inputRange = js.Array(0, 1),
          outputRange = js.Array(0, Device.width / numberOfTabs))
      )
      View(style = styles.container)(
        View(style = styles.tabs)(
          tabOptions.zipWithIndex.map {
            case (o, i) => renderTabOption(o, i)
          }
        ),
        View(style = styles.border)(),
        AnimatedView2(style = js.Array(styles.tabUnderlineStyle(numberOfTabs),json(left = left)))(
          View(style = styles.triangle)()
        )
      )
    }

    def renderTabOption(name: String, page: Int) = {

      val isTabActive = propsDynamic.activeTab.asInstanceOf[Int] == page
      TouchableOpacity(style = styles.tab, key = name, onPress = () => propsDynamic.goToPage.asInstanceOf[js.Function1[Int, Unit]](page))(

        View()(
          Text(style = styles.text(isTabActive))(name)
        )
      )
    }
  }

  val tabOptions = List(
    "Workshops", "Day 1", "Day 2"
  )

  object styles extends UniversalStyleSheet {

    val container = style(flexDirection.column,
      alignItems.center,
      justifyContent.spaceAround,
      height := 50)

    val tab = style(flexOne,
      alignItems.center,
      justifyContent.center)

    val tabs = style(width := Device.width,
      height := 35,
      flexDirection.row,
      justifyContent.spaceAround)

    def text(active: Boolean) = {
      val c = if (active) Colors.green else Colors.black
      style(color := c,
        if (active) fontWeight.bold else fontWeight.normal
      )
    }

    val border = style(width := Device.width,
      height := 1,
      marginBottom := 7,
      backgroundColor := Colors.black)

    val triangle = style(
      width := 0,
      height := 0,
      marginTop := 0,
      backgroundColor := "transparent",
      borderStyle.solid,
      borderLeftWidth := 5,
      borderRightWidth := 5,
      borderBottomWidth := 10,
      borderLeftColor := "transparent",
      borderRightColor := "transparent",
      borderBottomColor := Colors.green,
      transform := js.Array(json(rotate = "180deg"))
    )

    def tabUnderlineStyle(i: Int) = style(
      position.absolute,
      width := Device.width / i,
      height := 10,
      borderColor := Colors.green,
      borderTopWidth := 2,
      marginBottom := 15,
      bottom := 0,
      flexDirection.row,
      justifyContent.center)

  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)
}

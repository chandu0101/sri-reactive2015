package sri.reactive2015.components

import org.scalajs.dom
import sri.reactive2015.components.info.Info
import sri.reactive2015.components.map.RMap
import sri.reactive2015.components.schedule.Schedule
import sri.reactive2015.components.speakers.Speakers
import sri.relay.RelayElementFactory._
import sri.relay.container.{Fragments, RelayContainerSpec}
import sri.relay.query.RelayQL
import sri.relay.{Relay, RelayComponent}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object Root {

  @ScalaJSDefined
  class Component extends RelayComponent[Unit, Unit] {
    def render() = {
       dom.window.console.log(propsDynamic.viewer)
      View(style = UniversalStyleSheet.wholeContainer)(
        NavBar(),
        ScrollableTabView(renderTabBar = () => TabBar())(
          Schedule(propsDynamic.viewer),
          Speakers(propsDynamic.viewer),
          RMap(),
          Info(propsDynamic.viewer)
        )
      )
    }
  }

  val ctor = getComponentConstructor(js.constructorOf[Component], classOf[Component])

  val container = Relay.createContainer(ctor, new RelayContainerSpec {
    override val fragments = Fragments("viewer" -> (() => js.eval(RelayQL(
      """
            fragment on ReindexViewer {
                  ${Speakers.getFragment('viewer')}
                  ${Info.getFragment('viewer')}
                  ${Schedule.getFragment('viewer')}
                }
      """))))
  })
}

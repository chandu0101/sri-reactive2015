package sri.reactive2015.components.schedule

import sri.core._
import sri.reactive2015.components.ScrollableTabView
import sri.relay.container.RelayContainer.Fragments
import sri.relay.container.RelayContainer.Fragments
import sri.relay.container.{Fragments, RelayContainerSpec}
import sri.relay.query.RelayQL
import sri.relay.{Relay, RelayComponent}
import sri.universal.components._
import sri.mobile.all._
import sri.relay.RelayElementFactory._
import sri.universal.styles.UniversalStyleSheet
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U,undefined => undefined}
import scalajs.js.Dynamic.{literal => json}


object Schedule {


   @ScalaJSDefined
   class Component extends RelayComponent[Props,Unit] {
      def render() = {
        val days = prepareSchedule
        View(style = UniversalStyleSheet.wholeContainer)(
          ScrollableTabView(renderTabBar = () => ScheduleTabBar(),topBar = true)(
           ScheduleDay(days.getOrElse(0,js.Array())),
           ScheduleDay(days.getOrElse(1,js.Array())),
           ScheduleDay(days.getOrElse(2,js.Array()))
          )
        )
      }

     def prepareSchedule = {
       val edges = propsDynamic.viewer.allEvents.edges.asInstanceOf[js.Array[js.Dynamic]].map(edge => edge.node)
       edges.groupBy(node => node.day.asInstanceOf[Int])
     }
    }

    case class Props()

    val ctor = getComponentConstructor(js.constructorOf[Component],classOf[Component])

   val container = Relay.createContainer(ctor,new RelayContainerSpec {
     override val fragments: Fragments = Fragments("viewer" -> (() => js.eval(RelayQL(
       """
         fragment on ReindexViewer {
                allEvents(first: 100) {
                  edges {
                    node {
                      day,
                      startsAt,
                      endsAt,
                      type,
                      excerpt,
                      title,
                      speaker {
                        firstName,
                        lastName,
                        image
                      }
                    }
                  }
                }
              }
       """))))
   })

  def apply(viewer : js.Dynamic ) = createRelayElement(container,json(viewer = viewer))
}

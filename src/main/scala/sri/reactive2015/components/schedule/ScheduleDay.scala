package sri.reactive2015.components.schedule

import sri.core._
import sri.mobile.all._
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}

object ScheduleDay {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = ScrollView(style = UniversalStyleSheet.wholeContainer)(
      props.events.toList.zipWithIndex.map {
        case (e, i) => ScheduleCell(e, i.toString)
      }
    )
  }

  case class Props(events: js.Array[js.Dynamic])

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(events: js.Array[js.Dynamic], key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElement(ctor, Props(events), key = key, ref = ref)

}

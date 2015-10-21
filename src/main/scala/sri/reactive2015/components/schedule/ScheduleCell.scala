package sri.reactive2015.components.schedule

import org.scalajs.dom
import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.{FontedText, Accordion, Icon}
import sri.reactive2015.utils.Colors
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet
import sri.universal.styles.UniversalStyleSheet._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{Math, UndefOr => U}


object ScheduleCell {


  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = {
      if (hasExcerpt) Accordion(underlayColor = "#f0f0f0",
        header = renderHeader(),
        content = renderContent(),
        easing = "easeOutCubic")()
      else renderHeader()
    }

    def hasExcerpt = {
      val event = props.event
      event.excerpt != null && !js.isUndefined(event.excerpt) && event.excerpt.toString.nonEmpty
    }

    def getTime(i: Int) = {
      val minutes = (i - Math.floor(i / 100) * 100).toString
      if (minutes.length == 1) s"${minutes}0"
      else s"${Math.floor(i / 100)}:$minutes"
    }

    def renderHeader() = {
      val event = props.event
      val borderColor: js.Dictionary[Any] = if (js.isUndefined(event.`type`)) js.Dictionary() else styles.getEventTypeStyle(event.`type`.asInstanceOf[Int])
      View(style = styles.headerContainer)(
        View(style = styleE(styles.leftColumn, styles.sized, styles.borderTop, styles.borderTopBig)())(
          FontedText(style = styles.time)(
            s"${getTime(event.startsAt.asInstanceOf[Int])} - ${getTime(event.endsAt.asInstanceOf[Int])}"
          ),
          event.speaker.firstName.toString.nonEmpty ?= FontedText(style = styles.speaker)(s"""${event.speaker.firstName.toString.head}. ${event.speaker.lastName}""")
        ),
        View(style = styleE(styles.rightColumn, styles.borderTop, borderColor)())(
          FontedText(style = styles.talk)(event.title.toString.toLowerCase.capitalize)
        ),
        hasExcerpt ?= View(style = styleE(styles.borderTop, styles.sized, styles.center, styles.chevronContainer, borderColor)())(
          Icon(name = "ion|ios-arrow-down", size = 20.0, color = Colors.grey, style = styles.chevron)()
        )
      )
    }

    def renderContent() = {
      val event = props.event
      View(style = styles.headerContainer)(
        View(style = styles.leftColumn)(
          Image(style = styles.avatar, source = ImageSource(uri = event.speaker.image.toString))()
        ),
        View(style = styles.rightColumnExcerpt)(
          FontedText(style = styles.talkExcerpt)(event.excerpt.toString)
        )
      )
    }
  }

  object styles extends UniversalStyleSheet {
    val container = style(
      flex := 1
    )
    val headerContainer = style(
      flex := 1,
      flexDirection.row
    )
    val time = style(
      color := Colors.green
    )
    val speaker = style(
      marginTop := 8,
      fontWeight._400,
      fontSize := 12,
      width := 105
    )
    val talk = style(
      fontSize := 13,
      flex := 1
    )
    val sized = style(
      height := 70
    )
    val leftColumn = style(
      paddingLeft := 10,
      paddingTop := 15,
      width := 105,
      flexDirection.column
    )
    val borderTopBig = style(
      borderTopWidth := 4
    )
    val borderTop = style(
      borderBottomWidth := 0,
      borderTopWidth := 1,
      borderLeftWidth := 0,
      borderRightWidth := 0,
      borderTopColor := Colors.grey
    )
    val rightColumn = style(
      paddingTop := 10,
      paddingRight := 15,
      paddingLeft := 3,
      flex := 1,
      height := 60,
      flexDirection.column,
      justifyContent.center
    )
    val rightColumnExcerpt = style(
      paddingTop := 10,
      paddingRight := 15,
      paddingLeft := 3,
      flex := 1,
      flexDirection.column,
      justifyContent.center
    )
    val avatar = style(
      marginLeft := 20,
      width := 45,
      height := 45,
      borderRadius := 23
    )
    val talkExcerpt = style(
      marginBottom := 20,
      marginRight := 10,
      fontSize := 13
    )
    val center = style(
      justifyContent.center,
      alignItems.center
    )
    val chevronContainer = style(
      width := 25
    )

    val chevron = style(
      marginRight := 10,
      marginLeft := 5,
      height := 20,
      width := 20
    )

    def getEventTypeStyle(i: Int) = {
      if (i < 1 || i > 4) center
      else {
        var color = ""
        i match {
          case 1 => color = Colors.blue
          case 2 => color = Colors.purple
          case 3 => color = Colors.green
          case 4 => color = Colors.yellow
        }
        val x = borderTopColor := color
        style(borderTopWidth := 4,
          x
        )
      }


    }

  }

  case class Props(event: js.Dynamic)

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(event: js.Dynamic, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElement(ctor, Props(event), key = key, ref = ref)


}

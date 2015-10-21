package sri.reactive2015.components.speakers

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.{FontedText, OverlayImage}
import sri.reactive2015.utils.{Colors, Device}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object Speaker {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = View(style = styles.listItem)(
       OverlayImage(source = ImageSource(props.speaker.image.toString))(
        FontedText(style = styles.speaker)(s"${props.speaker.firstName} ${props.speaker.lastName}"),
        FontedText(style = styles.speakerCompany)(s"${props.speaker.company.name}")
       )
    )
  }

  object styles extends UniversalStyleSheet {

    val listItem = style(width := Device.width / 2,
      height := 100
    )

    val speaker = style(fontWeight._400,
      position.absolute,
      top := 10,
      left := 3,
      fontSize := 20,
      color := Colors.white)

    val speakerCompany = style(position.absolute,
      bottom := 10,
      right := 10,
      fontSize := 16,
      color := Colors.white)

  }

  case class Props(speaker: js.Dynamic)

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(speaker: js.Dynamic, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElement(ctor, Props(speaker), key = key, ref = ref)

}

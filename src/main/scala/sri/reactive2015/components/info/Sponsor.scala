package sri.reactive2015.components.info

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.Link
import sri.reactive2015.utils.Device
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


object Sponsor {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = Link(props.link)(
      Image(style = styles.getImageForType(props.tpe), source = ImageSource(uri = props.image))()
    )
  }

  object styles extends UniversalStyleSheet {

    val image = style(marginTop := 10,
      marginBottom := 10,
      width := Device.width,
      resizeMode := "contain")

    def getImageForType(tpe: String) = {
      var h = 20
      tpe match {
        case "gold" => h = 30
        case "silver" => h = 20
        case "bronze" => h = 20
        case _ => h = 20
      }
      styleE(image)(height := h)
    }

  }

  case class Props(link: String, tpe: String, image: String)

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(link: String, tpe: String, image: String, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElement(ctor, Props(link, tpe, image), key = key, ref = ref)

}

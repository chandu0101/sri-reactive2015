package sri.reactive2015.components.info

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.FontedText
import sri.reactive2015.utils.{Colors, Device}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object RethinkingSingle {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = View(style = styles.container)(
      Image(source = ImageSource(uri = props.imageUrl), style = styles.image)(),
      FontedText(style = UniversalStyleSheet.styleE(styles.imageTitle)(UniversalStyleSheet.color := props.titleColor))(props.imageTitle),
      FontedText(style = UniversalStyleSheet.styleE(styles.title)(UniversalStyleSheet.color := props.titleColor))(props.title),
      FontedText(style = styles.text)(children)
    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(
      alignItems.center,
      justifyContent.center)

    val image = style(marginTop := 20,
      width := Device.width,
      height := 30,
      resizeMode := "contain")

    val imageTitle = style(marginTop := 5,
      fontSize := 16,
      marginBottom := 10)


    val title = style(paddingLeft := 20,
      paddingRight := 20,
      fontSize := 18,
      textAlign.center,
      width := Device.width)

    val text = style(
      marginTop := 20,
      paddingLeft := 20,
      paddingRight := 20,
      fontSize := 14,
      textAlign.center,
      width := Device.width,
      color := Colors.white
    )

  }

  case class Props(imageUrl: String, imageTitle: String, titleColor: String, title: String)

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(imageUrl: String, imageTitle: String, titleColor: String, title: String, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null)(children: ReactNode*) = createElementWithChildren(ctor, Props(imageUrl, imageTitle, titleColor, title), key = key, ref = ref)(children: _*)

}

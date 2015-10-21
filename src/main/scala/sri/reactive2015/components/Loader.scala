package sri.reactive2015.components

import sri.core._
import sri.mobile.all._
import sri.reactive2015.utils.Colors
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U, undefined}


object Loader {


  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = View(style = styles.container)(
      Image(style = styles.logo, source = ImageSource(uri = "https://reactive2015.com/assets/img/logo.png"))(),
      Spin(color = Colors.green, `type` = "Wave", size = 50.0, style = styles.spin)(),
      FontedText(style = styles.text)("Applying Some Sri Relay powered magic")
    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexOne,
      justifyContent.center,
      alignItems.center,
      backgroundColor := Colors.darkBlue)

    val logo = style(width := 200,
      height := 70,
      resizeMode := "contain")

    val text = style(fontSize := 14,
      marginTop := 30,
      color := Colors.white)

    val spin = style(marginTop := 20)
  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)
}

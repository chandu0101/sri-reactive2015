package sri.reactive2015.components

import sri.core._
import sri.mobile.all._
import sri.reactive2015.utils.Colors
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object NavBar {

  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = View(style = styles.container)(
      Image(style = styles.logo, source = ImageSource(uri = "https://reactive2015.com/assets/img/logo.png"))()
    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(padding := 0,
      height := 64,
      margin := 0,
      backgroundColor := Colors.darkBlue,
      flexDirection := "row",
      justifyContent := "center",
      alignItems := "center",
      borderBottomWidth := 2,
      borderBottomColor := Colors.green)

    val logo = style(
      marginTop := 10,
      width := 150,
      height := 30,
      resizeMode := "contain"
    )

  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)
}

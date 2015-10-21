package sri.reactive2015.components

import sri.core._
import sri.mobile.all._
import sri.reactive2015.utils.Device
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet
import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


object OverlayImage {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = Image(style = styles.img, source = props.source, resizeMode = ImageResizeMode.COVER)(
      View(style = styles.overlay)(
        children
      )
    )
  }

  case class Props(source: ImageSource)

  object styles extends UniversalStyleSheet {

    val img = style(height := 100)

    val overlay = style(position.absolute,
      backgroundColor := "rgba(0, 0, 0, 0.4)",
      top := 0,
      right := 0,
      left := 0,
      bottom := 0)
  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(source: ImageSource, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null)(children : ReactNode*) = createElementWithChildren(ctor, Props(source), key = key, ref = ref)(children: _*)

}

package sri.reactive2015.components

import sri.core._
import sri.mobile.all._
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object FontedText {


  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = Text(style = styles.text(props.style))(
      children
    )
  }

  object styles extends UniversalStyleSheet {

    def text(other: js.Dictionary[Any]) = styleE(other)(fontFamily := "Raleway")

  }

  case class Props(style: js.Dictionary[Any])

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(style: js.Dictionary[Any], key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null)(children: ReactNode*) = createElementWithChildren(ctor, Props(style), key = key, ref = ref)(children: _*)

}

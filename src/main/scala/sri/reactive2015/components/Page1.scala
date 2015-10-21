package sri.reactive2015.components

import sri.core.ElementFactory._
import sri.core.ReactComponent
import sri.universal.components.{Text, View}
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U, undefined}

object Page1 {


  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = View(style = UniversalStyleSheet.wholeContainer)(
      Text()("Page1")
    )
  }

  val factory = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(factory, key = key, ref = ref)
}

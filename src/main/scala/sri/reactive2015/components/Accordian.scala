package sri.reactive2015.components

import chandu0101.macros.tojs.JSMacro
import sri.core._
import sri.mobile.all._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr => U, undefined}


case class Accordion(key: U[String] = undefined,
                     style: U[js.Any] = undefined,
                     ref: U[AccordionM => _] = undefined,
                     underlayColor: U[String] = undefined,
                     easing: U[String] = undefined,
                     header: U[ReactElement] = undefined,
                     content: U[ReactElement] = undefined
                      ) {

  def apply() = {
    val props = JSMacro[Accordion](this)
    val a = load[js.Dynamic]("react-native-accordion")
    React.createElement(a, props)
  }

}

@js.native
trait AccordionM extends js.Object

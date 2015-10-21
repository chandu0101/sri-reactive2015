package sri.reactive2015.components

import chandu0101.macros.tojs.JSMacro
import sri.core._
import sri.mobile.all._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr => U, undefined}


case class Icon(key: U[String] = undefined,
                style: U[js.Any] = undefined,
                ref: U[IconM => _] = undefined,
                size: U[Double] = undefined,
                name: String,
                color: U[String] = undefined) {

  def apply() = {
    val props = JSMacro[Icon](this)
    val i = load[js.Dynamic]("react-native-icons").Icon
    React.createElement(i, props)
  }

}

@js.native
trait IconM extends js.Object
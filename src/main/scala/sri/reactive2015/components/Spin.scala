package sri.reactive2015.components

import chandu0101.macros.tojs.JSMacro
import sri.core._
import sri.mobile.all._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr => U, undefined}


case class Spin(key: U[String] = undefined,
                style: U[js.Any] = undefined,
                ref: U[SpinM => _] = undefined,
                `type`: U[String] = undefined,
                color: U[String] = undefined,
                size: U[Double] = undefined
                 ) {

  def apply() = {

    val props = JSMacro[Spin](this)
    val s = load[js.Dynamic]("react-native-spinkit")
    React.createElement(s, props)
  }

}

@js.native
trait SpinM extends js.Object
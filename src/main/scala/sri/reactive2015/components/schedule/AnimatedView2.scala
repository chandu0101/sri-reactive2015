package sri.reactive2015.components.schedule

import chandu0101.macros.tojs.JSMacro
import sri.core._
import sri.mobile.ReactNative

import scala.scalajs.js
import scala.scalajs.js.{UndefOr => U, undefined}


case class AnimatedView2(key: U[String] = undefined,
                         style: U[js.Any] = undefined,
                         ref: U[AnimatedViewM => _] = undefined) {

  def apply(children: ReactNode*) = {
    val props = JSMacro[AnimatedView2](this)
    val v = ReactNative.Animated.View
    React.createElement(v, props, children: _*)
  }

}

@js.native
trait AnimatedViewM extends js.Object
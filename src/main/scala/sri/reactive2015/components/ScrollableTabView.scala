package sri.reactive2015.components

import chandu0101.macros.tojs.JSMacro
import sri.core.{React, ReactElement, ReactNode}
import scala.scalajs.js
import sri.mobile.all._
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.{UndefOr => U, undefined}


case class ScrollableTabView(key: U[String] = undefined,
                             style: U[js.Any] = undefined,
                             topBar: U[Boolean] = undefined,
                             ref: U[ScrollableTabViewM => _] = undefined,
                             renderTabBar: U[() => ReactElement] = undefined
                              ) {

  def apply(children: ReactNode*) = {
    val props = JSMacro[ScrollableTabView](this)
    val sview = load[js.Dynamic]("react-native-scrollable-tab-view")
    React.createElement(sview, props, children: _*)
  }

}

@js.native
trait ScrollableTabViewM extends js.Object
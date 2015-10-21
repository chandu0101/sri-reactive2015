package sri.reactive2015.components

import sri.core._
import sri.mobile.ReactNative
import sri.mobile.all._
import sri.universal.components._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}

object Link {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = TouchableOpacity(onPress = () => ReactNative.LinkingIOS.openURL(props.uri))(
      children
    )
  }

  case class Props(uri: String)

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(uri: String, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null)(children: ReactNode*) = createElementWithChildren(ctor, Props(uri), key = key, ref = ref)(children: _*)

}

package sri.reactive2015.components.map

import sri.core._
import sri.mobile.all._
import sri.mobile.components.{MapViewAnnotation, MapView, MapViewRegion}
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


object RMap {

  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = {
      val venue = MapViewRegion(48.152, 17.116, 0, 0)
      val annot = MapViewAnnotation(48.152, 17.116)
      MapView(pitchEnabled = true,
        showsUserLocation = true,
        style = UniversalStyleSheet.wholeContainer,
        region = venue,
        annotations = Seq(annot)
      )()
    }
  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)
}

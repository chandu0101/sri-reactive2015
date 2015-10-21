package sri.reactive2015.components

import sri.core.ElementFactory._
import sri.core.ReactComponent
import sri.mobile.ReactNative
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


object HelloSriMobile {

  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = {
      View(style = styles.container)(
        ScrollableTabView(renderTabBar = () => TabBar())(
          Page1(),
          Page2(),
          Loader()
        )
      )
    }
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexOne)

    val image = style(width := 256, height := 256, margin := 20)

    val text = style(fontWeight._500,
      fontSize := 18,
      color := "white")

  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply() = createElementNoProps(ctor)

}


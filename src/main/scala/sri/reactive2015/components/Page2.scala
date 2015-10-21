package sri.reactive2015.components

import sri.core.ElementFactory._
import sri.core.{React, ReactElement, ReactComponent}
import sri.universal.components.{Text, View}
import sri.universal.styles.UniversalStyleSheet
import scalajs.js.Dynamic.{literal => json}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}

object Page2 {


   @ScalaJSDefined
   class Component extends ReactComponent[Unit, Unit] {
     def render() = {
       println(s"rendering page 2")
       View(style = UniversalStyleSheet.wholeContainer)(
         Text()("Page2")
       )
     }
   }

   val factory = getTypedConstructor(js.constructorOf[Component], classOf[Component])

   def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(factory, key = key, ref = ref)
 }


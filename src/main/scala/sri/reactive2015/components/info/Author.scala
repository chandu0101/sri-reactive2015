package sri.reactive2015.components.info

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.{FontedText, Icon, Link}
import sri.reactive2015.utils.Colors
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object Author {


  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = View(style = styles.container)(
      FontedText(style = styles.text)(s"Made with &lt;love/&gt; by Chandu0101"),
      View(style = styles.links)(
        Link("http://github.com/chandu0101")(
          Icon(name = "ion|social-github", size = 25.0, color = Colors.grey, style = styles.icon)()
        ),
        Link("http://twitter.com/chandu0101")(
          Icon(name = "ion|social-twitter", size = 25.0, color = Colors.grey, style = styles.icon)()
        )
      )

    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexDirection.column,
      alignItems.center,
      backgroundColor := Colors.darkBlue,
      paddingTop := 20)

    val links = style(height := 25,
      marginTop := 10,
      marginBottom := 20,
      flexDirection.row,
      alignItems.center,
      justifyContent.center)

    val icon = style(width := 30,
      height := 30,
      marginLeft := 10,
      marginRight := 10)

    val text = style(color := Colors.grey,
      fontSize := 13)

  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)

}

package sri.reactive2015.components.info

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.FontedText
import sri.reactive2015.utils.{Colors, Device}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U, undefined}


object Rethinking {

  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = View(style = styles.container)(
      FontedText(style = styles.title)("RETHINKING WEB DEVELOPMENT"),
      RethinkingSingle(imageUrl = "https://reactive2015.com/assets/img/companies/flux-logo.png",
        imageTitle = "Data Flow",
        titleColor = "blue",
        title = "REMOVE ACCIDENTAL COMPLEXITY FROM YOUR APPS")(
          "Is MVC dead? Should the complete application state be stored in a single global variable?\n          Can we have a database with full query support on the client? Is setState an antipattern?\n          Should all the data be passed through props?\n          Come to hear answers, to not only these questions, from creators of\n          DataScript, Este, CycleJS, RxJS, Cerebral, Mobservable and Fluxible!"
        ),
      RethinkingSingle(imageUrl = "https://reactive2015.com/assets/img/companies/rest-logo.png",
        imageTitle = "Rethinking REST",
        titleColor = "purple",
        title = "BUILD TRULY REALTIME APPLICATIONS WITH EASE")(
          "As the world moves towards a highly interactive web, HTTP is being replaced with\n          WebSockets and realtime communication is becoming a standard. We need a new solution that\n          will embrace the realtime nature of the new generation of Web.\n          Listen to the thoughts of the creators of Falcor, GraphQL, Firebase, Dato or swarm.js!"
        ),
      RethinkingSingle(imageUrl = "https://reactive2015.com/assets/img/companies/multiplatform-logo.png",
        imageTitle = "Multiplatfrom React",
        titleColor = "green",
        title = "LEARN ONCE, WRITE ANYWHERE")(
          " React is not only a DOM library anymore. Mobile developers are building iOS\n          (and Android soon too) apps using React Native, desktop applications are being built with\n          React on Electron and even D3 can be used with React.\n          Learn how React ecosystem evolves and grows beyond the web platform with talks from\n          people behind React Native Playground, Este Native, Electron!"
        ),
      RethinkingSingle(imageUrl = "https://reactive2015.com/assets/img/companies/react-logo.png",
        imageTitle = "On React",
        titleColor = "yellow",
        title = "LEVERAGE THE EXPANDING REACT ECOSYSTEM")(
          "It seems that a lot of great expansions to react have surfaced lately. They touch on\n          topics as form validations, css handling, routing and multi language support or even\n          bootstrap component sets.\n          We set up to bring you the creators of some of the finest expansions out there, such as\n          CSS Modules and React Form Validation."
        ),
      FontedText(style = styles.more)("And a ton more!")
    )
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexDirection.column,
      alignItems.center,
      justifyContent.center,
      backgroundColor := Colors.darkBlue,
      width := Device.width)

    val title = style(marginTop := 35,
      fontWeight._400,
      fontSize := 20,
      color := Colors.white)

    val more = style(marginTop := 10,
      marginBottom := 20,
      fontSize := 24,
      color := Colors.white)
  }

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(ctor, key = key, ref = ref)
}

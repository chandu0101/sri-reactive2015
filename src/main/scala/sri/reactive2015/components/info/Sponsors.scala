package sri.reactive2015.components.info

import sri.core._
import sri.mobile.all._
import sri.reactive2015.components.FontedText
import sri.reactive2015.utils.{Colors, Device}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


object Sponsors {

  @ScalaJSDefined
  class Component extends ReactComponent[Props, Unit] {
    def render() = {

      val sponsorTypes = getSponsors()
      View(style = styles.container)(
        FontedText(style = styles.title)("OUR SPONSORS"),
        FontedText(style = styles.title)("GOLD SPONSORS"),
        sponsorTypes("gold").zipWithIndex.map {
          case (n, i) => Sponsor(tpe = "gold",
            image = n.company.logo.toString,
            link = n.company.url.toString,
            key = s"gold $i")
        }
        ,
        FontedText(style = styles.title)("SILVER SPONSORS"),
        sponsorTypes("silver").zipWithIndex.map {
          case (n, i) => Sponsor(tpe = "silver",
            image = n.company.logo.toString,
            link = n.company.url.toString,
            key = s"silver $i")
        }
        ,
        FontedText(style = styles.title)("BRONGE SPONSORS"),
        sponsorTypes("bronze").zipWithIndex.map {
          case (n, i) => Sponsor(tpe = "bronze",
            image = n.company.logo.toString,
            link = n.company.url.toString,
            key = s"bronze $i")
        }
        ,
        FontedText(style = styles.title)("MEDIA SPONSORS"),
        sponsorTypes("media").zipWithIndex.map {
          case (n, i) => Sponsor(tpe = "media",
            image = n.company.logo.toString,
            link = n.company.url.toString,
            key = s"media $i")
        }
        ,
        FontedText(style = styles.thanks)(" Thanks to our sponsors who made this great event possible.")
      )
    }

    def getSponsors() = {
      val gold: js.Array[js.Dynamic] = js.Array()
      val silver: js.Array[js.Dynamic] = js.Array()
      val bronze: js.Array[js.Dynamic] = js.Array()
      val media: js.Array[js.Dynamic] = js.Array()

      props.sponsors.foreach(s => {
        val level = s.node.level.asInstanceOf[Int]
        level match {
          case 10 => gold.push(s.node)
          case 20 => silver.push(s.node)
          case 30 => bronze.push(s.node)
          case _ => media.push(s.node)
        }
      })
      Map("gold" -> gold.toList,
        "silver" -> silver.toList,
        "bronze" -> bronze.toList,
        "media" -> media.toList).withDefaultValue(List())
    }
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexDirection.column,
      alignItems.center,
      backgroundColor := Colors.white)

    val title = style(marginTop := 20,
      fontSize := 18,
      marginBottom := 10)

    val tpe = style(marginTop := 15,
      fontSize := 14,
      marginBottom := 5)

    val thanks = style(textAlign.center,
      width := Device.width,
      marginTop := 20,
      fontSize := 14,
      marginBottom := 10)

  }

  case class Props(sponsors: js.Array[js.Dynamic])

  val ctor = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(sponsors: js.Array[js.Dynamic], key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElement(ctor, Props(sponsors), key = key, ref = ref)

}

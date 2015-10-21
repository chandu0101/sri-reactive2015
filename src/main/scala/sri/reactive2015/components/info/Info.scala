package sri.reactive2015.components.info

import sri.reactive2015.utils.{Colors, Device}
import sri.relay.RelayElementFactory._
import sri.relay.container.RelayContainer.Fragments
import sri.relay.container.{Fragments, RelayContainerSpec}
import sri.relay.query.RelayQL
import sri.relay.{Relay, RelayComponent}
import sri.universal.components._
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}

object Info {

  @ScalaJSDefined
  class Component extends RelayComponent[Props, Unit] {
    def render() = {
      ScrollView(style = styles.container, contentContainerStyle = styles.center)(
        Rethinking(),
        Sponsors(propsDynamic.viewer.allSponsors.edges.asInstanceOf[js.Array[js.Dynamic]]),
        Author()
      )
    }
  }

  object styles extends UniversalStyleSheet {

    val container = style(flexOne,
      backgroundColor := Colors.darkBlue,
      width := Device.width)
    val center = style(flexDirection.column, alignItems.center)

  }

  case class Props()

  val ctor = getComponentConstructor(js.constructorOf[Component], classOf[Component])

  val container = Relay.createContainer(ctor,
    new RelayContainerSpec {
      override val fragments: Fragments = Fragments("viewer" -> (() => js.eval(RelayQL(
        """
          fragment on ReindexViewer {
                 allSponsors(first: 100) {
                   edges {
                     node {
                       level,
                       company {
                         logo
                         url
                       }
                     }
                   }
                 }
               }
        """))))
    })

  def apply(viewer: js.Dynamic, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createRelayElement(container, json(viewer = viewer), key = key, ref = ref)


}

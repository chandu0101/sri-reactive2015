package sri.reactive2015.components.speakers

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


object Speakers {

  @ScalaJSDefined
  class Component extends RelayComponent[Props, Unit] {
    def render() = {
      val speakers = propsDynamic.viewer.allSpeakers.edges.asInstanceOf[js.Array[js.Dynamic]]
      ScrollView(automaticallyAdjustContentInsets = false)(
        View(style = styles.list)(
          speakers.toList.filter(s => s.firstName.toString.nonEmpty).zipWithIndex.map {
            case (s, i) => Speaker(s.node, i.toString)
          }
        )
      )
    }
  }

  object styles extends UniversalStyleSheet {

    val list = style(flexDirection.row, flexWrap.wrap, flexOne)

  }

  case class Props()

  val ctor = getComponentConstructor(js.constructorOf[Component], classOf[Component])


  val container = Relay.createContainer(ctor, new RelayContainerSpec {
    override val fragments: Fragments = Fragments("viewer" -> (() => js.eval(RelayQL(
      """
        fragment on ReindexViewer {
                allSpeakers (first: 100) {
                  edges {
                    node {
                      firstName,
                      lastName,
                      image,
                      bio,
                      twitter,
                      web,
                      github,
                      companyRole,
                      company {
                        name,
                        logo,
                        url
                      }
                    }
                  }
                }
              }
      """))))
  })

  def apply(viewer: js.Dynamic, key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createRelayElement(container, json(viewer = viewer))


}

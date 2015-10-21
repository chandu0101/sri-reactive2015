package sri.reactive2015.components

import sri.core.ElementFactory._
import sri.core.ReactComponent
import sri.reactive2015.utils.Colors
import sri.universal.components.{Text, TouchableOpacity, View}
import sri.universal.styles.UniversalStyleSheet

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{UndefOr => U}


object TabBar {


  @ScalaJSDefined
  class Component extends ReactComponent[Unit, Unit] {
    def render() = {
      View(style = styles.tabs)(
        tabs.zipWithIndex.map {
          case (t, i) => renderTapOption(t, i)
        }
      )
    }

    def renderTapOption(tab: Tab, page: Int) = {
      val isTabActive = propsDynamic.activeTab.asInstanceOf[Int] == page
      TouchableOpacity(key = page.toString, onPress = () => propsDynamic.goToPage.asInstanceOf[js.Function1[Int, Unit]](page))(
        View(style = styles.tab)(
          Icon(name = s"ion|${tab.icon}", size = 30.0, color = if (isTabActive) Colors.green else Colors.grey,style = styles.tabIcon)(),
          Text(style = if (isTabActive) styles.tabTextActive else styles.tabText)(
            tab.name
          )
        )
      )
    }
  }

  val tabs = List(
    Tab("Schedule", "ios-calendar-outline"),
    Tab("Speakers", "mic-b"),
    Tab("Map", "map"),
    Tab("Info", "ios-information-outline")
  )

  case class Tab(name: String, icon: String)

  object styles extends UniversalStyleSheet {

    val tab = style(flexOne,
      flexDirection.column,
      alignItems.center,
      justifyContent.center,
      marginLeft := 10,
      marginRight := 10,
      width := 65)

    val tabs = style(height := 60,
      flexDirection.row,
      alignItems.center,
      justifyContent.center,
      borderBottomWidth := 0,
      borderTopWidth := 2,
      borderLeftWidth := 0,
      borderRightWidth := 0,
      borderTopColor := "#1bce7c",
      backgroundColor := "#212739")

    val tabIcon = style(width := 30, height := 30)

    val tabText = style(color := "#cdcdcd")

    val tabTextActive = style(color := "#1bce7c")

  }

  val factory = getTypedConstructor(js.constructorOf[Component], classOf[Component])

  def apply(key: js.UndefOr[String] = js.undefined, ref: js.Function1[Component, _] = null) = createElementNoProps(factory, key = key, ref = ref)
}

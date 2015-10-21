package sri.reactive2015

import sri.mobile.ReactNative
import sri.mobile.all._
import sri.reactive2015.components.info.Info
import sri.reactive2015.components.schedule.Schedule
import sri.reactive2015.components.speakers.Speakers
import sri.reactive2015.components.{Loader, Root}
import sri.reactive2015.queries.AppQuery
import sri.relay.Relay
import sri.relay.container.RelayRootContainer
import sri.relay.network.NetworkLayer

import scala.scalajs.js
import scala.scalajs.js.JSApp


object MobileApp extends JSApp {

  def main() = {

    //    Relay.injectNetworkLayer(new Reindex("https://integrated-branch-17.myreindex.com").getRelayNetworkLayer())
    Relay.injectNetworkLayer(new Reindex("https://elected-reactor-20.myreindex.com").getRelayNetworkLayer())

    js.Dynamic.global.Schedule = Schedule.container
    js.Dynamic.global.Info = Info.container
    js.Dynamic.global.Speakers = Speakers.container

    val root = createMobileRoot(
      RelayRootContainer(Component = Root.container,
        query = AppQuery(),
        renderLoading = () => Loader()
      )
    )
    ReactNative.AppRegistry.registerComponent("SriReactive2015", () => root)
  }
}

@js.native
class Reindex(var url: String) extends js.Object {

  def getRelayNetworkLayer(): NetworkLayer = js.native
}
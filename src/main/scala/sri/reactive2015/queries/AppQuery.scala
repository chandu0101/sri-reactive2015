package sri.reactive2015.queries

import sri.relay.container.RootQueries
import sri.relay.query.RelayQL
import sri.relay.route.RelayQueryConfig

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => json}

object AppQuery {

  def apply() = new RelayQueryConfig {

    override val queries = RootQueries("viewer" -> (() => js.eval(RelayQL( """ query { viewer } """))))
    override val name: String = "AppQuery"
    override val params = json()
  }

}

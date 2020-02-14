package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import mydatamodels.core.application.http.HttpCommon


/**
 * Expected output:
 * {"player": <number of win>, "computer": <number of win>}
 *
 * @param system
 */
case class GetResults(system: ActorSystem) extends HttpCommon {


  val route =
    get {
      pathPrefix("results") {
        pathEndOrSingleSlash {

          completeJson(StatusCodes.OK, "{\"player\": <number of win>, \"computer\": <number of win>}")

        }

      }

    }
}
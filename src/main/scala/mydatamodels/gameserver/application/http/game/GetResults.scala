package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.infrastructure.InMemoryMatchRecorder
import mydatamodels.gameserver.application.injection.Module


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

          val (humanWins, computerWins) = Module.DefaultMatchRecorder.getRoundResults()
          val json = s"""{\"player\": ${humanWins}, \"computer\": ${computerWins}}"""
          completeJson(StatusCodes.OK, json)

        }

      }

    }
}
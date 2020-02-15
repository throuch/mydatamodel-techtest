package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.domain.repositories.ScoreRecord
import mydatamodels.core.infrastructure.InMemoryMatchRepository
import mydatamodels.gameserver.application.GameApp
import mydatamodels.gameserver.application.injection.Module
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI
import mydatamodels.rps.infrastructure.InMemoryGameRecorder


/**
 * Expected output:
 * {"player": <number of win>, "computer": <number of win>}
 *
 * @param system
 */
case class GetResults(system: ActorSystem) extends HttpCommon with GameAPI {

  val route = results

  override def results =
    get {
      pathPrefix("results") {
        pathEndOrSingleSlash {

          val ScoreRecord(_, computerWins, humanWins) = InMemoryMatchRepository.getScoreView(GameApp.matchID)
          val json = s"""{\"player\": ${humanWins}, \"computer\": ${computerWins}}"""
          completeJson(StatusCodes.OK, json)

        }

      }

    }
}
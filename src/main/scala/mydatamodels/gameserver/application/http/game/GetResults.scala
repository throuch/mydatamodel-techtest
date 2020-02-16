package mydatamodels.gameserver.application.http.game

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.domain.repositories.ScoreRecord
import mydatamodels.core.infrastructure.InMemoryMatchRepository
import mydatamodels.gameserver.application.GameApp
import mydatamodels.gameserver.application.injection.Module
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI
import mydatamodels.gameserver.interfaces.swagger.model.GameActionResponse
import mydatamodels.rps.infrastructure.InMemoryGameRecorder


/**
 * Expected output:
 * {"player": <number of win>, "computer": <number of win>}
 *
 * @param system
 */
class GetResults(system: ActorSystem) extends HttpCommon with GameAPI with JsonSupport {

  case class ScoreResponse(player: Int, computer: Int)

  implicit val responseFormat = jsonFormat2(ScoreResponse)

  val route = results

  override def results =
    get {
      pathPrefix("results") {
        pathEndOrSingleSlash {

          val ScoreRecord(_, computerWins, humanWins) = InMemoryMatchRepository.getScoreView(GameApp.matchID)
          complete(StatusCodes.OK, ScoreResponse(humanWins, computerWins))
        }
      }
    }
}
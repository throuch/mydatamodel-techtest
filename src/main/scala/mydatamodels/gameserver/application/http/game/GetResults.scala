package mydatamodels.gameserver.application.http.game

import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.domain.repositories.ScoreRecord
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.gameserver.interfaces.swagger.converter.JsonSupport


/**
 * Expected output:
 * {"player": <number of win>, "computer": <number of win>}
 *
 *
 */
class GetResults(implicit appContext: GameApplicationMixing) extends HttpCommon with JsonSupport {

  case class ScoreResponse(player: Int, computer: Int)

  implicit val responseFormat = jsonFormat2(ScoreResponse)

  val route = results

  def results =
    get {
      pathPrefix("results") {
        pathEndOrSingleSlash {

          val ScoreRecord(_, computerWins, humanWins) =
            appContext.getScoreView(
              appContext.defaultMatchID)
          complete(StatusCodes.OK, ScoreResponse(humanWins, computerWins))
        }
      }
    }
}
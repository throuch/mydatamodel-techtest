package mydatamodels.gameserver.application.http.game

import java.time.LocalDate

import akka.http.scaladsl.model.StatusCodes
import mydatamodels.core.application.http.HttpCommon
import mydatamodels.core.interfaces.GameConfiguration
import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.gameserver.application.injection.GameApplicationMixing

/**
 *
 * Create a match against computer for Rock-Paper-Scissors game
 *
 *
 */
class Create(implicit appContext: GameApplicationMixing) extends HttpCommon  {

  val route = create

  def create =

    path("create") {
      pathEnd {
        post {
          val match_id = appContext.createRockPaperScissorsGame(GameConfiguration(Human, Computer))
          val defaultPlayerID = appContext.createHumanPlayer("Default User", LocalDate.parse("1977-05-30"))
          appContext.registerHumanPlayers(match_id, defaultPlayerID)


          complete(
            StatusCodes.OK,
            match_id.toString)

        }
      }
    }
}
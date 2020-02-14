package mydatamodels.gameserver.application

import java.time.LocalDate

import mydatamodels.core.interfaces.GameConfiguration
import mydatamodels.core.interfaces.PlayerType._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object GameApp extends App {

  import injection.Module._


  val matchID =
    DefaultGameService.createRockPaperScissorsGame(GameConfiguration(Human, Computer))

  val playerID = DefaultGameService.createHumanPlayer("Thomas", LocalDate.parse("1977-05-30"))
  DefaultGameService.registerHumanPlayers(matchID, playerID)

  DefaultGameService.start(matchID)


  //  Await.result(system.whenTerminated, Duration.Inf)


}

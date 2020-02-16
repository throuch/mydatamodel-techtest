package mydatamodels.gameserver.application

import java.time.LocalDate

import akka.actor.ActorSystem
import mydatamodels.core.interfaces.GameConfiguration
import mydatamodels.core.interfaces.PlayerType._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object GameApp extends App {

  import injection.Module._

  implicit val system = ActorSystem("GameSystem")
  sys.addShutdownHook(system.terminate())

  val matchID =
    DefaultGameService.createRockPaperScissorsGame(GameConfiguration(Human, Computer))

  val playerID = DefaultGameService.createHumanPlayer("Thomas", LocalDate.parse("1977-05-30"))
  DefaultGameService.registerHumanPlayers(matchID, playerID)


  DefaultGameService.start


}

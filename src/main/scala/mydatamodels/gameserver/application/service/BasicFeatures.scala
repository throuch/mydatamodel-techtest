package mydatamodels.gameserver.application.service

import java.time.LocalDate

import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.core.interfaces.{GameConfiguration, MatchID, PlayerID}
import mydatamodels.gameserver.interfaces.GameService

trait BasicFeatures {
  self: GameService ⇒

  lazy val matchID =
    self.createRockPaperScissorsGame(GameConfiguration(Human, Computer))

  lazy val playerID = self.createHumanPlayer("Thomas", LocalDate.parse("1977-05-30"))
  self.registerHumanPlayers(matchID, playerID)

  def getDefaultPlayer(): PlayerID = playerID

  def getDefaultMatch(): MatchID = matchID

}

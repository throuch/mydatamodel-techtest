package mydatamodels.gameserver.application.service

import java.time.LocalDate

import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.core.interfaces.{GameConfiguration, MatchID, PlayerID}
import mydatamodels.gameserver.interfaces.GameService

trait BasicFeatures {
  self: GameService ⇒

  var matchID = matchGen()
  val playerID = self.createHumanPlayer("Default User", LocalDate.parse("1977-05-30"))
  self.registerHumanPlayers(matchID, playerID)

  private def matchGen(): MatchID = self.createRockPaperScissorsGame(GameConfiguration(Human, Computer))

  def getDefaultPlayer(): PlayerID = playerID

  def getDefaultMatch(): MatchID = matchID

  def resetDefaultMatch() = {
    matchID = matchGen()
  }
}

package mydatamodels.gameserver.application.service

import java.time.LocalDate

import mydatamodels.core.interfaces.PlayerType.{Computer, Human}
import mydatamodels.core.interfaces.{GameConfiguration, MatchID}
import mydatamodels.gameserver.interfaces.GameService

trait BasicFeatures {
  self: GameService =>

  var defaultMatchID: MatchID = createNewMatch()
  val defaultPlayerID = self.createHumanPlayer("Default User", LocalDate.parse("1977-05-30"))
  self.registerHumanPlayers(defaultMatchID, defaultPlayerID)


  def resetDefaultMatch() =
    defaultMatchID = createNewMatch()


  private def createNewMatch(): MatchID = self.createRockPaperScissorsGame(GameConfiguration(Human, Computer))

}

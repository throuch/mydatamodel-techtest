package mydatamodels.gameserver.application.service

import mydatamodels.core.application.service.MatchService
import mydatamodels.core.interfaces.{GameConfiguration, MatchID}


trait GameLauncher {
  self: MatchService =>

  def createRockPaperScissorsGame(config: GameConfiguration): MatchID =
    createGame(config).id

}

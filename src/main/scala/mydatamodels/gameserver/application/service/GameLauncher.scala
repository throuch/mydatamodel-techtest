package mydatamodels.gameserver.application.service

import akka.actor.ActorSystem
import mydatamodels.core.application.service.MatchService
import mydatamodels.core.interfaces.{GameConfiguration, MatchID}
import mydatamodels.gameserver.application.http.GameHttpServer
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.rps.application.actors.ClassicGameActor


trait GameLauncher {
  self: MatchService =>


  def createRockPaperScissorsGame(config: GameConfiguration): MatchID = {
    createGame(config).id
  }


}

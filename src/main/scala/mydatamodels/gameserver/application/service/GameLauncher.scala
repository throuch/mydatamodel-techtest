package mydatamodels.gameserver.application.service

import akka.actor.ActorSystem
import mydatamodels.core.application.service.{CommonGameService, MatchService}
import mydatamodels.core.domain.entities.Match
import mydatamodels.core.interfaces.{GameConfiguration, MatchID}
import mydatamodels.gameserver.application.http.GameHttpServer
import mydatamodels.rps.application.actors.ClassicGameActor
import mydatamodels.rps.domain.ClassicGame

import scala.collection.mutable


trait GameLauncher {
  self: CommonGameService with MatchService =>


  def createRockPaperScissorsGame(config: GameConfiguration): MatchID = {
    create(config).id
  }


  /**
   * start a game and blocks until game server termination
   *
   */
  def start(implicit system: ActorSystem): Unit = {


    val gameActorRef = system.actorOf(ClassicGameActor.props(self), "GameActor")

    new GameHttpServer(gameActorRef)

  }

}

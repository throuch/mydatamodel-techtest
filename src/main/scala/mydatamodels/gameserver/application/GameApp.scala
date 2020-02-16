package mydatamodels.gameserver.application

import akka.actor.ActorSystem
import mydatamodels.gameserver.application.http.GameHttpServer
import mydatamodels.gameserver.application.injection.GameApplicationMixing
import mydatamodels.rps.application.actors.ClassicGameActor

object GameApp extends App with GameApplicationMixing {

  implicit val system = ActorSystem("GameSystem")
  implicit val instance = this

  sys.addShutdownHook(system.terminate())

  val gameActorRef = system.actorOf(ClassicGameActor.props(instance), "GameActor")

  new GameHttpServer(gameActorRef)


}

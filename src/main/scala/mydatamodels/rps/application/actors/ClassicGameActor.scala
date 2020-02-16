package mydatamodels.rps.application.actors

import akka.actor.{Actor, ActorLogging, Props}
import mydatamodels.core.application.service.MatchService
import mydatamodels.core.interfaces.MatchID
import mydatamodels.gameserver.interfaces.swagger.model.GameAction
import mydatamodels.rps.domain.ClassicGame

import scala.collection.mutable


/**
 *
 *
 */
class ClassicGameActor(matchService: MatchService) extends Actor with ActorLogging {


  // this is just a cache it is STATELESS !
  val games: mutable.Map[MatchID, ClassicGame] = new mutable.HashMap[MatchID, ClassicGame]()

  override def receive: Receive = {
    case (matchId: MatchID, body: GameAction) =>
      sender() ! getGameReference(matchId).onHumanAction(body)
    case msg ⇒ log.warning(s"DEBUG: unrecognized message $msg")
  }

  def createClassicGame(matchId: MatchID): ClassicGame = {
    val game = new ClassicGame(matchService.read(matchId), matchService)
    if (!game.`match`.isMatchReadyToStart)
      throw new IllegalStateException
    game
  }

  def getGameReference(matchId: MatchID): ClassicGame = {
    games.getOrElseUpdate(matchId, createClassicGame(matchId))
  }
}

object ClassicGameActor {
  def props(matchService: MatchService): Props =
    Props(new ClassicGameActor(matchService))

}

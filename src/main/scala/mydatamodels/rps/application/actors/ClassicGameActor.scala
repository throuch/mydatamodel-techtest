package mydatamodels.rps.application.actors

import akka.actor.{Actor, ActorLogging, Props}
import mydatamodels.core.application.service.MatchService
import mydatamodels.core.interfaces.MatchID
import mydatamodels.gameserver.application.injection.ComputerAI
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.domain.ClassicGame
import mydatamodels.rps.infrastructure.inMemoryGameRecorder
import mydatamodels.rps.interfaces.DomainConverter
import mydatamodels.rps.interfaces.RPSElement.RPSElement

import scala.collection.mutable
import scala.util.control.NonFatal


/**
 *
 *
 */
class ClassicGameActor(appContext: MatchService with ComputerAI) extends Actor with ActorLogging {


  // this is just a cache it is STATELESS !
  val games: mutable.Map[MatchID, ClassicGame] = new mutable.HashMap[MatchID, ClassicGame]()

  override def receive: Receive = {
    case (matchId: MatchID, body: GameAction) =>
      try {
        sender() ! onHumanAction(getGameReference(matchId), body)
      }
      catch {
        case NonFatal(e) =>
          sender ! akka.actor.Status.Failure(e)
        //          log.error(e.getMessage, e)
      }

    case msg => log.warning(s"DEBUG: unrecognized message $msg")
  }

  def createClassicGame(matchId: MatchID): ClassicGame = {
    val `match` = appContext.read(matchId)
    val game = new ClassicGame(`match`, inMemoryGameRecorder, appContext.defaultGameStrategy)
    if (!`match`.isMatchReadyToStart)
      throw new IllegalStateException
    game
  }

  def getGameReference(matchId: MatchID): ClassicGame = {
    if (!appContext.matchRepo.exists(matchId)) throw new Exception("Match ID not found")
    games.getOrElseUpdate(matchId, createClassicGame(matchId))
  }

  def formatMatchResult(
                         computerElement: RPSElement,
                         humanElement: RPSElement,
                         humanResultWin: Boolean): String = {
    val result = if (humanResultWin) "win" else "lose"
    s"You played $humanElement, I played $computerElement, you $result"
  }

  def onHumanAction(game: ClassicGame, action: GameAction): GameActionResponse = {

    val (humanHand, computerHand, _, humanResultWin) =
      game.playRound(DomainConverter.toDomain(action.myHand))

    GameActionResponse(humanResultWin,
      formatMatchResult(DomainConverter.toApi(computerHand),
        DomainConverter.toApi(humanHand),
        humanResultWin))

  }

}

object ClassicGameActor {
  def props(appContext: MatchService with ComputerAI): Props =
    Props(new ClassicGameActor(appContext))

}

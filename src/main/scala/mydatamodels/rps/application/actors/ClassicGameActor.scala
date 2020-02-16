package mydatamodels.rps.application.actors

import akka.actor.{Actor, Props}
import mydatamodels.gameserver.interfaces.swagger.model.GameAction
import mydatamodels.rps.domain.ClassicGame


/**
 * TODO use a game repository STATELESS
 *
 * @param game
 */
class ClassicGameActor(game: ClassicGame) extends Actor {

  override def receive: Receive = {
    case action: GameAction =>
      sender() ! game.onHumanAction(action)

  }
}

object ClassicGameActor {
  def props(game: ClassicGame): Props =
    Props(new ClassicGameActor(game))

}

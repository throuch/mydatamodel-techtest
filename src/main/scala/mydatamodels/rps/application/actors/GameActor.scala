package mydatamodels.rps.application.actors

import akka.actor.{Actor, Props}
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}

class GameActor extends Actor {
  override def receive: Receive = {
    case o@GameAction(_) => {



      sender() ! GameActionResponse(1, "STUB")

    }
  }
}

object GameActor {
  def props(): Props =
    Props(new GameActor)

}

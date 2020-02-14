package mydatamodels.rps.application.actors

import akka.actor.{Actor, Props}
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.domain.{ClassicGame, ComputerAI, DomainConverter}
import mydatamodels.rps.interfaces.RPSElement.RPSElement

class GameActor(game: ClassicGame) extends Actor {

  object GameResult extends Enumeration {
    type GameResult = Value
    val win, lose = Value

  }

  def formatMatchResult(/*computer: Player,
                        human:Player,*/
                        computerElement: RPSElement,
                        humanElement: RPSElement,
                        humanResult: GameResult.GameResult): String = {
    s"You played $humanElement, I played $computerElement, you $humanResult"
  }

  def getHumanResult(computerElement: RPSElement,
                     humanElement: RPSElement): GameResult.GameResult = {
    val result = game.gameEngine.compare(
      DomainConverter.toDomain(computerElement),
      DomainConverter.toDomain(humanElement))

    if (result >= 0)
      GameResult.lose
    else
      GameResult.win

  }

  def onHumanAction(action: GameAction): GameActionResponse = {

    val humanHand = action.myHand
    val computerHand = ComputerAI.getHand()

    val humanResult = getHumanResult(computerHand, humanHand)

    GameActionResponse(humanResult == GameResult.win,
      formatMatchResult(computerHand,
        humanHand, humanResult))

  }

  override def receive: Receive = {
    case action: GameAction => {

      sender() ! onHumanAction(action)

    }
  }
}

object GameActor {
  def props(game: ClassicGame): Props =
    Props(new GameActor(game))

}

package mydatamodels.rps.domain

import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.domain.{ComputerPlayer1, ComputerPlayer2, Game, Player}
import mydatamodels.gameserver.application.injection.Module
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.interfaces.RPSElement.RPSElement


/**
 *
 *
 *
 **/
class ClassicGame(_m: Match) extends Game[ClassicElement](_m, new ClassicGameEngine) {


  object GameResult extends Enumeration {
    type GameResult = Value
    val win, lose = Value

  }

  def formatMatchResult(
                         computerElement: RPSElement,
                         humanElement: RPSElement,
                         humanResult: GameResult.GameResult): String = {
    s"You played $humanElement, I played $computerElement, you $humanResult"
  }

  /**
   *
   * @param computerElement
   * @param humanElement
   * @return win/lose for the human player
   */
  def computeGame(computerElement: RPSElement,
                  humanElement: RPSElement): GameResult.GameResult = {
    val result = gameEngine.compare(
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

    val humanResult = computeGame(computerHand, humanHand)


    Module.DefaultMatchRecorder.recordRoundResult(humanResult == GameResult.win)
    ComputerAI.record(computerHand, humanHand)

    GameActionResponse(humanResult == GameResult.win,
      formatMatchResult(computerHand,
        humanHand, humanResult))

  }


}

package mydatamodels.rps.domain

import mydatamodels.core.domain.Game
import mydatamodels.core.domain.entities.{Match}
import mydatamodels.rps.domain.repositories.GameRecorder


/**
 *
 **/
class ClassicGame(_m: Match, gameRecorder: GameRecorder, ai: AIStrategy) extends Game[ClassicElement](_m, new ClassicGameEngine) {

  object GameResult extends Enumeration {
    type GameResult = Value
    val win, lose, draw = Value
  }

  def playRound(humanHand: ClassicElement) = {
    val computerHand = ai.getHand(`match`.id)

    val humanResult = computeGame(computerHand, humanHand)
    val humanResultWin: Boolean = humanResult == GameResult.win

    gameRecorder.push(humanHand,
      computerHand,
      humanResultWin,
      `match`.id)

    if (humanResultWin)
      _m.incrementVisitorScore()
    else
      _m.incrementHomeScore()
    (humanHand, computerHand, humanResult, humanResultWin)
  }

  /**
   *
   * @param computerElement
   * @param humanElement
   * @return win/lose/draw for the human player
   */
  def computeGame(computerElement: ClassicElement,
                  humanElement: ClassicElement): GameResult.GameResult = {
    val result = gameEngine.compare(
      computerElement,
      humanElement)

    if (result > 0)
      GameResult.lose
    else if (result < 0)
      GameResult.win
    else
      GameResult.draw
  }


}

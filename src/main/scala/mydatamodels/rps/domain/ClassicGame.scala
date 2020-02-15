package mydatamodels.rps.domain

import mydatamodels.core.domain.Game
import mydatamodels.core.domain.entities.Match
import mydatamodels.gameserver.application.injection.Module.DefaultMatchRecorder
import mydatamodels.gameserver.application.injection.Module.DefaultComputerAI
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
  def computeGame(computerElement: ClassicElement,
                  humanElement: ClassicElement): GameResult.GameResult = {
    val result = gameEngine.compare(
      computerElement,
      humanElement)

    if (result >= 0)
      GameResult.lose
    else
      GameResult.win

  }

  def onHumanAction(action: GameAction): GameActionResponse = {

    val humanHand = DomainConverter.toDomain(action.myHand)
    val computerHand = DefaultComputerAI.getHand()

    val humanResult = computeGame(computerHand, humanHand)


    DefaultMatchRecorder.recordRoundResult(humanResult == GameResult.win)
    DefaultComputerAI.record(computerHand, humanHand)

    GameActionResponse(humanResult == GameResult.win,
      formatMatchResult(DomainConverter.toApi(computerHand),
        DomainConverter.toApi(humanHand), humanResult))

  }


}

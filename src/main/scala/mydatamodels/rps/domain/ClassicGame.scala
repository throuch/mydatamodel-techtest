package mydatamodels.rps.domain

import mydatamodels.core.application.service.MatchService
import mydatamodels.core.domain.Game
import mydatamodels.core.domain.entities.Match
import mydatamodels.gameserver.application.injection.DefaultComputerAI
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import mydatamodels.rps.infrastructure.InMemoryGameRecorder
import mydatamodels.rps.interfaces.RPSElement.RPSElement


/**
 *
 **/
class ClassicGame(_m: Match, matchService: MatchService) extends Game[ClassicElement](_m, new ClassicGameEngine) {


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
    val computerHand = DefaultComputerAI.getHand(`match`.id)

    val humanResult = computeGame(computerHand, humanHand)
    val humanResultWin: Boolean = humanResult == GameResult.win

    InMemoryGameRecorder.push(humanHand,
      computerHand,
      humanResultWin,
      `match`.id)

    if (humanResultWin) {
      matchService.incrementHumanScore(`match`.id)
    }
    else {
      matchService.incrementComputerScore(`match`.id)
    }

    GameActionResponse(humanResultWin,
      formatMatchResult(DomainConverter.toApi(computerHand),
        DomainConverter.toApi(humanHand), humanResult))

  }


}

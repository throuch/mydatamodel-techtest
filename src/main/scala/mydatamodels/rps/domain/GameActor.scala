package mydatamodels.rps.domain

import mydatamodels.core.interfaces.{MatchID, PlayerID}
import mydatamodels.rps.infrastructure.KeyboardInput
import mydatamodels.rps.interfaces.{PlayerAction, RPSElement}

import scala.util.Random

trait GameActor {

}


class CPUActor(override val id: PlayerID) extends GameInput {

  override def getNextAction(matchID: MatchID): PlayerAction = {
    val elementValues= RPSElement.values.toIndexedSeq
    PlayerAction(
      elementValues(Random.nextInt(elementValues.size)),
      matchID,
      id )
  }
}

class HumanActor(override val id: PlayerID) extends GameInput {

  override def getNextAction(matchID: MatchID): PlayerAction = {
    PlayerAction(
      KeyboardInput.readElement(),
      matchID,
      id )
  }
}

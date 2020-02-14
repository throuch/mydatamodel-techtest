package mydatamodels.rps.domain

import mydatamodels.core.interfaces.{MatchID, PlayerID}
import mydatamodels.rps.interfaces.PlayerAction

trait GameInput {
  val id: PlayerID

  /**
   * Remark: this is a blocking operation
   *
   * @param matchID
   * @return
   */
  def getNextAction(matchID: MatchID): PlayerAction
}





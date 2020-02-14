package mydatamodels.rps.interfaces

import RPSElement.RPSElement
import mydatamodels.core.interfaces._

/**
 * this is the standard version of the game Rock-Paper-Scissors
 */
case class PlayerAction(element: RPSElement, matchID: MatchID, playerID: PlayerID)




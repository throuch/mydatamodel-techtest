package mydatamodels.core.interfaces

import mydatamodels.core.interfaces.PlayerType.PlayerType


/**
 *
 * Basic game configuration involving 2 players
 *
 * @param playerOne
 * @param playerTwo
 * @param roundCount
 */
case class GameConfiguration(playerOne: PlayerType,
                             playerTwo: PlayerType,
                             roundCount: Short = 1) {

}

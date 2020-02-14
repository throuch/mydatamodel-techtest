package mydatamodels.gameserver.interfaces

import java.time.LocalDate

import mydatamodels.core.interfaces._

/**
 *
 * The general service allowing to manage different games
 *
 */
trait GameService {

  /**
   * start the game
   *
   * @param
   */
  def start(matchID: MatchID): Unit

  /**
   * create a new Rock-Paper-Scissors game
   *
   * @param
   * @return
   */
  def createRockPaperScissorsGame(config: GameConfiguration): MatchID

  /**
   * register one or several human players to an existing match
   *
   * @param matchID
   * @param playerID
   *
   * return an exception if the expected number of players is wrong
   */
  def registerHumanPlayers(matchID: MatchID, playerID: PlayerID*): Unit

  /**
   * create a new player
   * return an exception if the player is under 18 years
   **/
  def createHumanPlayer(pseudo: String, birthdate: LocalDate): PlayerID

  def getRoundResult(matchId: MatchID, roundIndex: RoundIndex): RoundResult

  def getMatchResult(matchId: MatchID): MatchResult

}

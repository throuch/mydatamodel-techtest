package mydatamodels.core.application.service

import java.time.LocalDate

import mydatamodels.core.domain.entities.{HumanPlayer, Match}
import mydatamodels.core.interfaces.PlayerType._
import mydatamodels.core.interfaces._

import scala.collection.mutable

trait CommonGameService {

  // TODO to be removed for STATELESS
  val matches: mutable.Map[MatchID, Match] = mutable.HashMap[MatchID, Match]()
  val players: mutable.Map[PlayerID, HumanPlayer] = mutable.HashMap[PlayerID, HumanPlayer]()


  /**
   * Instanciate a new match
   *
   * @param config the game configuration
   * @return a match
   * @see nicecactus.core.domain.entities.Match
   */
  def createMatch(config: GameConfiguration): Match = {
    val _match =
      if (((config.playerOne == Human) && (config.playerTwo == Computer)) ||
        ((config.playerOne == Computer) && (config.playerTwo == Human))
      ) {
        new Match(roundCount = config.roundCount, expectHumanPlayersCount = 1)
      }
      else if ((config.playerOne == Computer) && (config.playerTwo == Computer)) {
        new Match(roundCount = config.roundCount, expectHumanPlayersCount = 0)
      }
      else { // human Vs human
        throw new UnsupportedOperationException("2 humans players mode not yet supported")
        //new Match(roundCount = _config.roundCount, expectHumanPlayersCount = 2)
      }

    matches.put(_match.id, _match)
    _match
  }

  /**
   * register one or several human players to an existing match
   *
   * @param matchID
   * @param playerID
   * @exception UnsupportedOperationException if the supported number of players is wrong
   * @exception IllegalArgumentException if the player doesn't exist
   */
  def registerHumanPlayers(matchID: MatchID, playerID: PlayerID*): Unit = {
    if (playerID.size == 1) {
      if (!players.contains(playerID(0)))
        throw new IllegalArgumentException(s"Unknown player ID ${playerID(0)}")
      if (!matches.contains(matchID))
        throw new IllegalArgumentException(s"Unknown match ID ${matchID}")

      matches.get(matchID).get.registerPlayerOne(players.get(playerID(0)).get)
    } else
      throw new UnsupportedOperationException("2 humans players mode not yet supported")
  }

  /**
   * create a new player
   *
   * @exception if the player is under 18 years
   **/
  def createHumanPlayer(pseudo: String, birthDate: LocalDate): PlayerID = {
    val player = new HumanPlayer(pseudo = pseudo, birthDate = birthDate)
    players.put(player.id, player)
    player.id
  }

  def getRoundResult(matchId: MatchID, roundIndex: RoundIndex): RoundResult = ???

  def getMatchResult(matchId: MatchID): MatchResult = ???
}

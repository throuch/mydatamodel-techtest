package mydatamodels.core.application.service

import java.time.LocalDate

import mydatamodels.core.domain.entities.HumanPlayer
import mydatamodels.core.domain.repositories.PlayerRepository
import mydatamodels.core.interfaces._

import scala.collection.mutable

trait CommonGameService {
  self: MatchService with PlayerRepository ⇒

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
      if (!doesExists(matchID))
        throw new IllegalArgumentException(s"Unknown match ID ${matchID}")

      registerPlayer(matchID, playerID(0))

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

}

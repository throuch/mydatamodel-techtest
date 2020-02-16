package mydatamodels.core.application.service

import mydatamodels.core.domain.entities.Match
import mydatamodels.core.domain.repositories.{MatchRepository, PlayerRepository, ScoreRecord}
import mydatamodels.core.infrastructure.InfraConverter
import mydatamodels.core.interfaces.{GameConfiguration, MatchID, PlayerID}
import mydatamodels.core.interfaces.PlayerType._

trait MatchService {
  self: MatchRepository with PlayerRepository ⇒

  def getScoreView(matchId: MatchID): ScoreRecord = {
    get(matchId).map(prev ⇒
      ScoreRecord(matchId, prev.computerScore, prev.humanScore)).getOrElse(ScoreRecord(matchId, -1, -1))
  }

  def incrementHumanScore(matchId: MatchID): Unit =
    get(matchId).foreach(prev ⇒
      put(matchId, prev.copy(humanScore = prev.humanScore + 1)))

  def incrementComputerScore(matchId: MatchID): Unit =
    get(matchId).foreach(prev ⇒
      put(matchId, prev.copy(computerScore = prev.computerScore + 1)))


  //def getAllScoreViews(matchId: MatchID): Seq[ScoreRecord]

  /**
   * Instanciate a new match and store it in the repository
   *
   * @param config the game configuration
   * @return a match
   */
  def create(config: GameConfiguration): Match = {
    val `match` =
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

    register(`match`)
    `match`
  }

  /** store a match entity
   */
  def register(m: Match): Unit = {
    put(m.id, InfraConverter.toInfra(m))
  }

  def doesExists(matchId: MatchID): Boolean =
  // this.asInstanceOf[MatchRepository].exists(matchId) /// ???? TODO
    self.exists(matchId)

  /** get a match entity from the repository
   * retrieve players
   */
  def read(matchID: MatchID): Match = {
    get(matchID).map(m ⇒ {

      val player = players.getOrElse(m.humanPlayerId, throw new Exception(s"Player ${m.humanPlayerId} not found"))

      val matchEntity = InfraConverter.toDomain(m)
      matchEntity.registerPlayerOne(player)

      matchEntity
    }).getOrElse(throw new Exception(s"Match $matchID NOT FOUND"))

  }

  /**
   * Update operation
   *
   * @param matchID
   * @param playerID
   * @return
   */
  def registerPlayer(matchID: MatchID, playerID: PlayerID) = {
    val v = get(matchID).getOrElse(throw new Exception("NOT FOUND"))

    put(matchID, v.copy(humanPlayerId = playerID))

  }
}

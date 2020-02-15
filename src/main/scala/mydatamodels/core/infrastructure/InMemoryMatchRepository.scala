package mydatamodels.core.infrastructure

import java.util.UUID

import mydatamodels.core.domain.repositories.{MatchObjectValue, MatchRepository, ScoreRecord}
import mydatamodels.core.interfaces.MatchID

import scala.collection.mutable

object InMemoryMatchRepository extends MatchRepository {
  //val DEFAULT_MATCH_ID = UUID.randomUUID()

  val matchDb = mutable.HashMap[MatchID, MatchObjectValue]()


  override def count: Int = matchDb.size

  //override def get(matchId: MatchID): Option[MatchObjectValue] = matchDb.get(matchId)

  override def incrementHumanScore(matchId: MatchID): Unit = {
    val prev = matchDb.getOrElseUpdate(matchId, MatchObjectValue(matchId))
    matchDb += (matchId → prev.copy(humanScore = prev.humanScore + 1))
  }

  override def incrementComputerScore(matchId: MatchID): Unit = {
    val prev = matchDb.getOrElseUpdate(matchId, MatchObjectValue(matchId))
    matchDb += (matchId → prev.copy(computerScore = prev.computerScore + 1))
  }

  // override def put(value: MatchObjectValue): Unit = matchDb + (value.matchId → value)


  override def delete(matchId: MatchID): Unit = {
    matchDb.remove(matchId)
  }

  override def getScoreView(matchId: MatchID): ScoreRecord = {
    val r = matchDb.getOrElseUpdate(matchId, MatchObjectValue(matchId))
    ScoreRecord(matchId, r.computerScore, r.humanScore)
  }
}

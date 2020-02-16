package mydatamodels.core.domain.repositories

import java.util.UUID

import mydatamodels.core.interfaces.MatchID


case class ScoreRecord(matchId: MatchID, computerScore: Int = 0, humanScore: Int = 0)

case class MatchObjectValue(matchId: MatchID, computerScore: Int = 0, humanScore: Int = 0 /* to be completed */)


/**
 * stores information about matches and scores in particular
 *
 * TODO think about what is about service and what is about pure repository
 *
 */
trait MatchRepository {

  def count: Int

  def delete(matchId: MatchID): Unit

  /*
  def get(matchId: MatchID): Option[MatchObjectValue]

  def put(value: MatchObjectValue): Unit
*/

  def incrementHumanScore(matchId: MatchID)

  def incrementComputerScore(matchId: MatchID)


  def getScoreView(matchId: MatchID): ScoreRecord

  //def getAllScoreViews(matchId: MatchID): Seq[ScoreRecord]

  def getOrCreate(matchId: MatchID = UUID.randomUUID()): MatchObjectValue

}

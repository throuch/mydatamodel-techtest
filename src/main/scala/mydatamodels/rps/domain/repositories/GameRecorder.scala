package mydatamodels.rps.domain.repositories

import mydatamodels.core.interfaces.MatchID
import mydatamodels.rps.domain.ClassicElement


case class GameRecord(matchId: MatchID,
                      roundIndex: Int,
                      humanElement: ClassicElement,
                      computerElement: ClassicElement,
                      humanWins: Boolean)


/**
 * Records the game events
 */
trait GameRecorder {

  def count(matchId: MatchID): Int

  def push(humanElement: ClassicElement,
           computerElement: ClassicElement,
           humanWins: Boolean,
           matchId: MatchID
          )


  def getGameRecords(matchId: MatchID): Seq[GameRecord]
}

package mydatamodels.rps.infrastructure

import mydatamodels.core.interfaces.MatchID
import mydatamodels.rps.domain.ClassicElement
import mydatamodels.rps.domain.repositories.{GameRecord, GameRecorder}

import scala.collection.mutable

/**
 * For the moment don't take matchId and roundindex into account
 */
object InMemoryGameRecorder extends GameRecorder {
  val actions = new mutable.Queue[(ClassicElement, ClassicElement, Boolean)]

  override def count(matchId: MatchID): Int = actions.size

  override def push(humanElement: ClassicElement,
                    computerElement: ClassicElement,
                    humanWins: Boolean,
                    matchId: MatchID): Unit = {
    actions += ((humanElement, computerElement, humanWins))

  }

  override def getGameRecords(matchId: MatchID): Seq[GameRecord] = {
    actions.zipWithIndex.map(x => GameRecord(
      matchId,
      x._2,
      x._1._1,
      x._1._2,
      x._1._3
    )).sortBy(_.roundIndex)
  }.toSeq
}

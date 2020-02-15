package mydatamodels.rps.domain

import mydatamodels.core.interfaces.MatchID
import mydatamodels.rps.domain.repositories.GameRecorder

import scala.util.Random


trait RoundRobinStrategy {
  var idx = 0

  def getHand(): ClassicElement = {
    val elementValue = ClassicElementValues.values.toIndexedSeq(idx)
    idx = (idx + 1) % ClassicElementValues.values.size
    elementValue
  }
}

/**
 * this strategy looks to all the hands of the human player and plays with more probability
 * the element beating the element most frequently played by the human
 */
trait AdvancedGameStrategy {
  val repo: GameRecorder

  def getHand(matchId: MatchID): ClassicElement = {

    val (r, p, s) = computeHumanRockPaperScissorsRatio(matchId)

    val pool = IndexedSeq.fill[ClassicElement](r)(Paper) ++
      IndexedSeq.fill[ClassicElement](p)(Scissors) ++
      IndexedSeq.fill[ClassicElement](s)(Rock)
    val total = pool.size

    pool(Random.nextInt(total))
  }

  def computeHumanRockPaperScissorsRatio(matchId: MatchID): (Int, Int, Int) = {
    val totalRounds = repo.count(matchId)

    if (totalRounds <= 3) {
      (1, 1, 1)
    }
    else {
      val records = repo.getGameRecords(matchId)
      val totalR = records.count(_.humanElement == Rock).toDouble
      val totalP = records.count(_.humanElement == Paper).toDouble
      val totalS = records.count(_.humanElement == Scissors).toDouble
      ((totalR / totalRounds * 100).toInt, (totalP / totalRounds * 100).toInt, (totalS / totalRounds * 100).toInt)
    }
  }
}

trait ClassicGameStrategy {
  def getHand(): ClassicElement = {
    val elementValues = ClassicElementValues.values.toIndexedSeq
    elementValues(Random.nextInt(elementValues.size))
  }
}
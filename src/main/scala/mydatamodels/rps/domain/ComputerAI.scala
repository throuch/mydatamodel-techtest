package mydatamodels.rps.domain

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
  this: PlayActionRecorder ⇒
  def getHand(): ClassicElement = {

    val (r, p, s) = computeHumanRockPaperScissorsRatio()

    val pool = IndexedSeq.fill[ClassicElement](r)(Paper) ++
      IndexedSeq.fill[ClassicElement](p)(Scissors) ++
      IndexedSeq.fill[ClassicElement](s)(Rock)
    val total = pool.size

    pool(Random.nextInt(total))
  }

  def computeHumanRockPaperScissorsRatio(): (Int, Int, Int) = {
    val totalRounds = this.humanActions.size

    if (totalRounds <= 3) {
      (1, 1, 1)
    }
    else {
      val totalR = this.humanActions.count(_ == Rock).toDouble
      val totalP = this.humanActions.count(_ == Paper).toDouble
      val totalS = this.humanActions.count(_ == Scissors).toDouble
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
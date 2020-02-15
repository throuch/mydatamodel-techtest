package mydatamodels.rps.domain


import mydatamodels.rps.interfaces.RPSElement
import mydatamodels.rps.interfaces.RPSElement.RPSElement

import scala.util.Random

object ComputerAI extends PlayActionRecorder with AdvancedGameStrategy {


}


trait RoundRobinStrategy {
  var idx = 0

  def getHand(): RPSElement = {
    val elementValue = RPSElement.values.toIndexedSeq(idx)
    idx = (idx + 1) % RPSElement.values.size
    elementValue
  }
}

/**
 * this strategy looks to all the hands of the human player and plays with more probability
 * the element beating the element most frequently played by the human
 */
trait AdvancedGameStrategy {
  this: PlayActionRecorder ⇒
  def getHand(): RPSElement = {

    val (r, p, s) = computeHumanRockPaperScissorsRatio()

    val pool = IndexedSeq.fill[RPSElement](r)(RPSElement.paper) ++
      IndexedSeq.fill[RPSElement](p)(RPSElement.scissors) ++
      IndexedSeq.fill[RPSElement](s)(RPSElement.rock)
    val total = pool.size

    pool(Random.nextInt(total))
  }

  def computeHumanRockPaperScissorsRatio(): (Int, Int, Int) = {
    val totalRounds = this.humanActions.size

    if (totalRounds <= 3) {
      (1, 1, 1)
    }
    else {
      val totalR = this.humanActions.count(_ == RPSElement.rock).toDouble
      val totalP = this.humanActions.count(_ == RPSElement.paper).toDouble
      val totalS = this.humanActions.count(_ == RPSElement.scissors).toDouble
      ((totalR / totalRounds * 100).toInt, (totalP / totalRounds * 100).toInt, (totalS / totalRounds * 100).toInt)
    }
  }
}

trait ClassicGameStrategy {
  def getHand(): RPSElement = {
    val elementValues = RPSElement.values.toIndexedSeq
    elementValues(Random.nextInt(elementValues.size))
  }
}
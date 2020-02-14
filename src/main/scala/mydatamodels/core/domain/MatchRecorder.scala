package mydatamodels.core.domain


/**
 * This define the behavior of a component recording the results of each round in a
 * match human Vs computer
 */
trait MatchRecorder {


  def recordRoundResult(humanWins: Boolean)

  /**
   * reset winner counters
   */
  def resetResults

  /**
   *
   * @return a couple (human wins count, computer wins count)
   */
  def getRoundResults(): (Int, Int)
}

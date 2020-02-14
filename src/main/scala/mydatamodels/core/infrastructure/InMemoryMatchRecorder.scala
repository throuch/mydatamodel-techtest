package mydatamodels.core.infrastructure

import mydatamodels.core.domain.MatchRecorder

object InMemoryMatchRecorder extends MatchRecorder {
  var humanWinsCount =0
  var computerWinsCount = 0

  override def recordRoundResult(humanWins: Boolean): Unit = {
    if (humanWins)
      humanWinsCount +=1
    else
      computerWinsCount+=1
  }


  override def getRoundResults(): (Int, Int) = (humanWinsCount, computerWinsCount)

  override def resetResults: Unit = {
    humanWinsCount= 0
    computerWinsCount = 0
  }
}

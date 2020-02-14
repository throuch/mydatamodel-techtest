package mydatamodels.core.domain

import org.scalatest.{FlatSpec, Matchers}

class ComputerTest extends FlatSpec with Matchers {

  it should "compare constants" in {
    assert(ComputerPlayer1 != ComputerPlayer2)
  }

}

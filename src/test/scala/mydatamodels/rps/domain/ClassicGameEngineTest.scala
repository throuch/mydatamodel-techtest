package mydatamodels.rps.domain

import org.scalatest.{FlatSpec, Matchers}

class ClassicGameEngineTest extends FlatSpec with Matchers {
  val gameEngine = new ClassicGameEngine()

  behavior of "ClassicGameEngineTest"

  it should "compare paper Vs scissor" in {
    assert(gameEngine.compare(Paper, Scissors) < 0)
  }

  it should "compare paper Vs paper" in {
    assert(gameEngine.compare(Paper, Paper) == 0)
  }

  it should "compare paper Vs rock" in {
    assert(gameEngine.compare(Paper, Rock) > 0)
  }

  it should "compare all combinations" in {
    val expectedMatchResults = List(
      (Paper, Scissors, -1),
      (Scissors, Paper, 1),

      (Scissors, Rock, -1),
      (Rock, Scissors, 1),

      (Rock, Paper, -1),
      (Paper, Rock, 1),

      (Paper, Paper, 0),
      (Rock, Rock, 0),
      (Scissors, Scissors, 0))

    expectedMatchResults.foreach(_ match {
      case (e1: ClassicElement, e2: ClassicElement, expected: Int) =>
        assert(gameEngine.compare(e1, e2) == expected)
      case _ => fail("test implementation error")
    })
  }


}

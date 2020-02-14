package mydatamodels.rps.domain

import mydatamodels.rps.interfaces.RPSElement
import mydatamodels.rps.interfaces.RPSElement.RPSElement

import scala.util.Random

object ComputerAI {

  def getHand(): RPSElement = {
    val elementValues = RPSElement.values.toIndexedSeq
    elementValues(Random.nextInt(elementValues.size))

  }
}

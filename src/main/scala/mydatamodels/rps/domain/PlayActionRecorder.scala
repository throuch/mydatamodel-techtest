package mydatamodels.rps.domain

import mydatamodels.rps.interfaces.RPSElement.RPSElement

import scala.collection.mutable

trait PlayActionRecorder {
  val humanActions = new mutable.Queue[RPSElement]
  val computerActions = new mutable.Queue[RPSElement]


  def record(computerElement: RPSElement, humanElement: RPSElement, roundIndex: Int = 0) = {
    humanActions += humanElement
    computerActions += computerElement
  }

}

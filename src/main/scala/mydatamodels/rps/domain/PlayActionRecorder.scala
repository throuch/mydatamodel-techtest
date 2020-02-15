package mydatamodels.rps.domain


import scala.collection.mutable

trait PlayActionRecorder {
  val humanActions = new mutable.Queue[ClassicElement]
  val computerActions = new mutable.Queue[ClassicElement]


  def record(computerElement: ClassicElement, humanElement: ClassicElement, roundIndex: Int = 0) = {
    humanActions += humanElement
    computerActions += computerElement
  }

}

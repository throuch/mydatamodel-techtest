package mydatamodels.rps.domain

import mydatamodels.core.domain.GameEngine

class ClassicGameEngine extends GameEngine[ClassicElement] {

  /**
   * Compare 2 elements in the same manner as the Comparator interface
   *
   * @see java.util.Comparator#compare
   *
   *      So: 1 means "beats", 0 means "equal", -1 means "lose"
   * @param leftElement
   * @param rightElement
   * @return -1,0,1
   */
  override def compare(leftElement: ClassicElement, rightElement: ClassicElement): Int = {

    if ((leftElement == Rock && rightElement == Scissors) ||
      (leftElement == Paper && rightElement == Rock) ||
      (leftElement == Scissors && rightElement == Paper))
      1
    else if (leftElement == rightElement)
      0
    else
      -1
  }

}

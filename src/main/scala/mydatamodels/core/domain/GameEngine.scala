package mydatamodels.core.domain

import java.util.Comparator


/**
 * Game engine describe the mechanics and the logical rules defining the game.
 * This is the core of the domain.
 */
abstract class GameEngine[E <: Element] extends Comparator[E] {


}

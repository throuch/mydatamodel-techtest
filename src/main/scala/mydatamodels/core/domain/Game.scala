package mydatamodels.core.domain

import mydatamodels.core.domain.entities.Match


abstract class Game[T <: Element](val `match`: Match, val gameEngine: GameEngine[T]) {

}

package mydatamodels.rps.domain

import mydatamodels.core.domain.Element

sealed trait ClassicElement extends Element


case object Rock extends ClassicElement
case object Scissors extends ClassicElement
case object Paper extends ClassicElement
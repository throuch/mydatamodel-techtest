package mydatamodels.gameserver.interfaces.swagger.model

import io.swagger.annotations.{ApiModel, ApiModelProperty}
import mydatamodels.rps.interfaces.RPSElement.RPSElement

import scala.annotation.meta.field


@ApiModel(description = "Scala model containing an Enumeration Value")
case class GameAction(
                       @(ApiModelProperty@field)(value = "myHand",
                         dataType = "mydatamodels.rps.interfaces.RPSElement$") myHand: RPSElement)


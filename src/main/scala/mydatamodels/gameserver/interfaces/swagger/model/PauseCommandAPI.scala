package mydatamodels.gameserver.interfaces.swagger.model

import io.swagger.annotations.ApiModel


@ApiModel(description = "Scala model containing an Enumeration Value")
case class PauseCommandAPI(
                            /* @(ApiModelProperty@field)(value = "Task ID") destTaskId: UUID,
                               @(ApiModelProperty@field)(value = "Pause",
                                 dataType = "abacus.order.domain.event.PauseCommand$") pause: PauseCommand*/)


package mydatamodels.gameserver.interfaces.swagger.model

import java.util.UUID

import io.swagger.annotations.ApiModel

@ApiModel(description = "Order submission response")
case class SubmissionResponse
(
  //  @(ApiModelProperty@field)(value = "Status", dataType = "abacus.order.domain.OperationStatus$") status: OperationStatus.OperationStatus,
  returnCode: Int,
  message: String,
  orderExecutionId: Option[UUID] = None
)
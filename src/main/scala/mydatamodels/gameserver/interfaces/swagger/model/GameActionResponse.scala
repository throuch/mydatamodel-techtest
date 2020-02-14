package mydatamodels.gameserver.interfaces.swagger.model

import java.util.UUID

import io.swagger.annotations.ApiModel

@ApiModel(description = "Game action response")
case class GameActionResponse
(
  humanWins: Boolean,
  message: String
)
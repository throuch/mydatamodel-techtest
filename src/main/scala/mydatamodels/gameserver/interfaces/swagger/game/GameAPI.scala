package mydatamodels.gameserver.interfaces.swagger.game

import akka.http.scaladsl.server.Route
import io.swagger.annotations._
import javax.ws.rs.Path
import mydatamodels.gameserver.interfaces.swagger.model.{GameAction, GameActionResponse}
import akka.http.scaladsl.server.Route
import io.swagger.annotations._

@Path("/play")
@Api(value = "GameAction")
@SwaggerDefinition(tags = Array(new Tag(name = "hello", description = "operations useful for debugging")))
trait GameAPI {
  @ApiOperation(
    value = "Play Rock or Paper or Scissors",
    nickname = "play",
    httpMethod = "POST",
    consumes = "application/json",
    produces = "text/plain",
    response = classOf[GameActionResponse])
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(
        name = "body",
        value = "Game action",
        dataTypeClass = classOf[GameAction],
        required = true,
        paramType = "body"
      )
    )
  )
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "You played <play>, I played <AI-play>, you <win/lose>", response = classOf[GameActionResponse]),
    new ApiResponse(code = 418),
    new ApiResponse(code = 403, message = "if invalid input")
  ))
  def whatever: Option[Route] = None


}

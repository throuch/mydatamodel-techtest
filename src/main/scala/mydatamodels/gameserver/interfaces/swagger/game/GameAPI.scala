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
  /* @Path("{id}")
  @ApiOperation(
    value = "Retrieve an order by {id}",
    nickname = "getOrderByID",
    httpMethod = "GET",
    produces = "application/json",
    response = classOf[OrdersRow]
  )
  @ApiImplicitParams(Array(

    new ApiImplicitParam(
      name = "id",
      value = "ID of the order that needs to be retrieved",
      dataType = "string",
      format = "uuid",
      required = false,
      paramType = "path"
    )

  ))*/
  @ApiOperation(
    value = "Play Rock or Paper or Scissors",
    nickname = "play",
    httpMethod = "POST",
    consumes = "application/json",
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
    new ApiResponse(code = 418, message = "You played <play>, I played <AI-play>, you <win/lose>"),
    new ApiResponse(code = 403, message = "if invalid input")
  ))
  def whatever: Option[Route] = None

/*
  @ApiOperation(
    value = "Searches for orders by criteria",
    nickname = "searchOrders",
    httpMethod = "GET",
    produces = "application/json",
    response = classOf[OrdersRow]
  )
  @ApiImplicitParams(Array(

    new ApiImplicitParam(
      name = "X-Correlation-Id",
      value = "correlation id",
      dataType = "string",
      required = false,
      paramType = "header"
    ),
    new ApiImplicitParam(
      name = "state",
      dataType = "string",
      required = false,
      paramType = "query",
      allowableValues = "Open, Closed"
    ),
    new ApiImplicitParam(
      name = "status",
      dataType = "string",
      required = false,
      paramType = "query",
      allowableValues = "Success, Failure"
    ),
    new ApiImplicitParam(
      name = "external_ref",
      dataType = "string",
      required = false,
      paramType = "query"
    ),
    new ApiImplicitParam(
      name = "fromCreatedAt",
      dataType = "string",
      format = "date-time",
      required = false,
      paramType = "query"
    ),
    new ApiImplicitParam(
      name = "toCreatedAt",
      dataType = "string",
      format = "date-time",
      required = false,
      paramType = "query"
    )
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK"),
    new ApiResponse(code = 404, message = "Order not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 405, message = "Invalid input")
  ))
  def tata: Option[Route] = None


  @ApiOperation(
    value = "Submit an order to the OrderManager",
    nickname = "submitOrder",
    httpMethod = "POST",
    consumes = "application/json")
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(
        name = "body",
        value = "Order object that needs to be submitted",
        dataTypeClass = classOf[abacus.order.interfaces.OrderPB],
        required = true,
        paramType = "body"
      ),
      new ApiImplicitParam(
        name = "X-Correlation-Id",
        value = "correlation ID",
        dataType = "string",
        required = false,
        paramType = "header"
      )
    ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK", response = classOf[SubmissionResponse]),
    new ApiResponse(code = 400, message = "Invalid parameters", response = classOf[SubmissionResponse]),
    new ApiResponse(code = 500, message = "There was an internal server error.", response = classOf[SubmissionResponse])
  ))
  def submitOrder: Option[Route] = None
*/
}

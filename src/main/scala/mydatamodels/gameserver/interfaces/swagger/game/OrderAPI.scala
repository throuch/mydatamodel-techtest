package mydatamodels.gameserver.interfaces.swagger.game

import akka.http.scaladsl.server.Route
import io.swagger.annotations._
import javax.ws.rs.Path

@Path("/order")
@Api(value = "Orders")
@SwaggerDefinition(tags = Array(new Tag(name = "hello", description = "operations useful for debugging")))
trait OrderAPI {
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

  ))
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "OK", response = classOf[OrdersRow]),
    new ApiResponse(code = 404, message = "Order not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 405, message = "Invalid input")
  ))
  def titi: Option[Route] = None


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

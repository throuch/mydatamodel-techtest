package mydatamodels.gameserver.interfaces.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.{Contact, Info, License}
import com.typesafe.config.ConfigFactory
import mydatamodels.gameserver.interfaces.swagger.game.GameAPI

object SwaggerDocService extends SwaggerHttpService {
  val config = ConfigFactory.load()
  val API_URL = s"${sys.env("ADVERTISED_HOST")}:${sys.env("ADVERTISED_PORT")}"
  // OR  config.getString("swagger.api.url")

  val BASE_PATH = config.getString("swagger.api.base.path")
  val PROTOCOL = config.getString("swagger.api.scheme.protocol")

  override val apiClasses: Set[Class[_]] = Set(
    classOf[Ping],
    classOf[Status],
    classOf[GameAPI]
  )
  override val host = API_URL //the url of your api, not swagger's json endpoint
  override val apiDocsPath = "api-docs" //where you want the swagger-json endpoint exposed
  //override val securitySchemeDefinitions = Map("basicAuth" -> new BasicAuthDefinition())
  override val unwantedDefinitions = Seq(
    "Function1"
  )

  //  val buildInfo = Buil
  override val info = Info(
    "Swagger Akka http Order microservice....",
    "0.1.0",
    "Rock-Paper-Scissors",
    termsOfService = "http://swagger.io/terms/",
    license = Some(License("Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html")),
    contact = Some(Contact("Thomas ROUCH", "", "thomas.rouch.prestat@srr.fr"))

  )
}

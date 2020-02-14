package mydatamodels.gameserver.interfaces.swagger.converter

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import mydatamodels.gameserver.interfaces.swagger.model.GameAction
import mydatamodels.rps.interfaces.RPSElement
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat, RootJsonFormat}

object MyJsonProtocol extends DefaultJsonProtocol {

  implicit object UUIDJsonFormat extends JsonFormat[UUID] {
    def write(c: UUID) =
      JsString(c.toString)

    def read(value: JsValue) = value match {
      case JsString(uuid) =>
        UUID.fromString(uuid)
      case _ => throw DeserializationException("UUID expected")
    }
  }

  def enumFormat[T <: Enumeration](implicit enu: T): RootJsonFormat[T#Value] =
    new RootJsonFormat[T#Value] {
      def write(obj: T#Value): JsValue = JsString(obj.toString)

      def read(json: JsValue): T#Value = {
        json match {
          case JsString(txt) => enu.withName(txt)
          case somethingElse => throw DeserializationException(s"Expected a value from enum $enu instead of $somethingElse")
        }
      }
    }


}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  import MyJsonProtocol._

  implicit val element = enumFormat(RPSElement)
  implicit val action = jsonFormat1(GameAction)
}

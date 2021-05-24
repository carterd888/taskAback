package controllers
import models.Vehicle
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.Results.Ok
import play.api.mvc.{request, _}

import javax.inject._
import repositories.DataRepository

import scala.concurrent.{ExecutionContext, Future}
@Singleton
case class BasicController @Inject()(val controllerComponents: ControllerComponents,
                                     dataRepository: DataRepository, implicit val ec: ExecutionContext) extends BaseController {

//  def getOneVehicle(vehicleName: String) = Action { implicit request =>
//    Ok(Json.toJson(dataRepository.getVehicle(vehicleName)))}

  def receiveForm(): Action[AnyContent]= Action.async { implicit request: Request[AnyContent] =>
    val jsonReceived = request.body.asJson
    println("jsonReceived" + request.body)
    val vehicleNameFromJsonReceived = jsonReceived match {
      case Some(value) => jsonReceived.get.\("Vehicle Name").as[String]
      case None => "test"
    }
    dataRepository.getVehicle(vehicleNameFromJsonReceived).map(items => Ok(Json.toJson(items.head))) recover {
      case _ => InternalServerError(Json.obj(
        "message" -> "Error adding item to Mongo"
      ))
    }
  }






//  def create(): Action[JsValue] = Action.async(parse.json) { implicit request =>
//    request.body.validate[Vehicle] match {
//      case JsSuccess(vehicle, _) =>
//        dataRepository.create(vehicle).map(_ => Created)
//      case JsError(_) => Future(BadRequest)
//    }
//  }

//  def findAll(): Action[AnyContent] = Action.async { implicit request =>
//    dataRepository.getVehicle("Chopper").map(items => Ok(Json.toJson(items)))
//  }




}

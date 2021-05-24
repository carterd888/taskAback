package controllers

import models.Vehicle
import org.scalatest.Matchers._
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.mockito.Mockito.when
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status
import play.api.mvc.{Action, AnyContent, ControllerComponents, Result}
import play.api.test.{FakeRequest, Injecting}
import repositories.DataRepository
import play.api.test.Helpers._

import scala.concurrent.{Await, ExecutionContext, Future}
import org.mockito.ArgumentMatchers.any
import play.api.libs.json.{JsObject, Json}

class BasicControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]
  implicit lazy val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val mockDataRepository: DataRepository = mock[DataRepository]

  object testController extends BasicController(
    controllerComponents,
    mockDataRepository,
    executionContext
  )

  val dataModel: Vehicle = Vehicle(
    3,
    true,
    "BMW"
  )

//  "BasicController .getOneVehicle" should {
//    "return Ok" when {
//      "expected vehicle name submitted" in {
//        when(mockDataRepository.getVehicle(any[String]))
//          .thenReturn(Some(dataModel))
//        val result = testController.getOneVehicle("BMW")(FakeRequest())
//        status(result) mustBe (Status.OK)
//      }
//    }
//  }
//  "return NotFound" when {
//    "unexpected vehicle name submitted" in {
//      when(mockDataRepository.getVehicle(any[String]))
//        .thenReturn(None)
//
//      val result = testController.getOneVehicle("Fail")(FakeRequest())
//      status(result) mustBe (Status.NOT_FOUND)
//    }
//  }


  "BasicController .receiveForm" should {
    "return Ok" when {
      "expected vehicle name received from POST method" in {
        when(mockDataRepository.getVehicle(any[String]))
          .thenReturn(Future(Seq(dataModel)))
        val result = testController.receiveForm()(FakeRequest())
        status(result) mustBe (Status.OK)
        contentAsJson(result) shouldBe Json.toJson(dataModel)
      }
    }
  }
}

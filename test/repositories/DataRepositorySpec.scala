package repositories

import controllers.BasicController
import models.Vehicle
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.mvc.ControllerComponents
import play.api.test.Injecting

import scala.concurrent.ExecutionContext

class DataRepositorySpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]
  implicit lazy val executionContext: ExecutionContext = app.injector.instanceOf[ExecutionContext]
  val mockDataRepository: DataRepository = mock[DataRepository]

  object testController extends BasicController(
    controllerComponents,
    mockDataRepository,
    executionContext
  )

  "DataRepository .getVehicle" should {
    "return a vehicle" when {
      "vehicleNameFromUrl matches either 'BMW' or 'Chopper'" in {
//        when(mockDataRepository.getVehicle("BMW"))
//          .thenReturn(Option[Vehicle])
      }
    }
  }
}

package controllers

import javax.inject._
import play.api.mvc.{BaseController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ThemeController @Inject()(val controllerComponents: ControllerComponents,
                                implicit val ec: ExecutionContext)
  extends BaseController {


}

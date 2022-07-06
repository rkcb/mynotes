package controllers

import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class ThemeController @Inject()(val controllerComponents: ControllerComponents,
                                implicit val ec: ExecutionContext)
  extends BaseController {



}

package controllers

import db.PojoJson.NoteInputOutput
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import repos.{Failure, NoteRepo, Success}

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class NoteController @Inject()(val controllerComponents: ControllerComponents,
															 val noteRepo: NoteRepo,
															 implicit val ec: ExecutionContext)
  extends BaseController {

	def getThemes: Action[AnyContent] = Action.async { _ =>
		noteRepo.getThemes.map(themes => Ok(Json.toJson(themes)))
	}

	def getTags: Action[AnyContent] = Action.async { _ =>
		noteRepo.getTags.map(tags => Ok(Json.toJson(tags)))
	}


	def createNote(): Action[NoteInputOutput] =
		Action.async(parse.json[NoteInputOutput]) { request =>
		noteRepo.createNote(request.body).map {
			case Right(note) => Created(Json.toJson(note))
			case Left(error) => BadRequest(error)
		}



	}

}

package controllers

import db.PojoJson.NoteInputOutput
import play.api.http.Status.OK
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

class NoteControllerSpec extends TestWithTestDb {

	before {
		cleanDatabase()
	}

	"NoteController" should {

		"get themes" in {
			val request = FakeRequest(GET, "/api/themes")
			val result = route(app, request).get
			status(result) mustBe OK
		}

		"get tags" in {
			val request = FakeRequest(GET, "/api/tags")
			val result = route(app, request).get
			status(result) mustBe OK
		}

		"create a note with POST" in {
			val note = NoteInputOutput(
				id = None, themeId = "scala", note = "my test note", tags = None)
			val request = FakeRequest(POST, "/api/note")
				.withJsonBody(Json.toJson(note))

			val result = route(app, request).get
			status(result) mustBe CREATED
			val json = contentAsJson(result)
			val note2 = Json.fromJson[NoteInputOutput](json).get
			note2.id.nonEmpty mustBe true
		}


		"modify note" in {

		}

		"delete note" in {

		}

		"create tags" in {

		}

		"delete tags" in {

		}


	}


}

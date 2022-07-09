package controllers

import com.typesafe.config.{Config, ConfigFactory}
import db.org.jooq.Tables._
import db.org.jooq.tables.records.NoteRecord
import models.DbApi
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers.{await, defaultAwaitTimeout}

import java.io.File
import javax.inject.Inject
import scala.concurrent.Future

// see GuiceOneAppPerSuite for app overrides
class ConfSpec extends TestWithTestDb {

	before {
		cleanDatabase()
	}


	"Test conf db " should {

		"have url with test" in {
			db.url.contains("test") mustBe true
		}

		"conf must be defined" in {
			true mustBe true
			val res = db.action( jooq =>
				jooq.fetchCount(NOTE)
			)

			await(res) mustBe 0

		}
	}

}

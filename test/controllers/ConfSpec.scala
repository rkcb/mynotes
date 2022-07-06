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
class ConfSpec extends PlaySpec with GuiceOneAppPerSuite {

	val conf = ConfigFactory.parseFile(new File("conf/application.conf"))

	override def fakeApplication(): Application = new GuiceApplicationBuilder()
		.configure(Map("db.default.url" -> conf.getString("notes.test.url")))
		.build()

	lazy val db = app.injector.instanceOf[DbApi]

	"Test conf db " should {
		"conf must be defined" in {
			true mustBe true
			val res = db.action( jooq =>
				jooq.fetchCount(NOTE)
			)

			await(res) mustBe 0

		}
	}

}

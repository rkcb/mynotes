package controllers

import com.sun.tools.javac.resources.ct
import models.DbApi
import org.jooq.scalaextensions.Conversions._
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.Helpers
import play.api.test.Helpers.{await, defaultAwaitTimeout}

import scala.concurrent.Future
import scala.jdk.CollectionConverters._
import db.org.jooq.Tables._
import db.org.jooq.tables.records.NoteRecord
import org.scalatest.BeforeAndAfter


class DbSpec extends TestWithTestDb {


	before {
		db.jooqContext.truncate(NOTE)
		db.jooqContext.truncate(NOTE_TAG)
		db.jooqContext.truncate(TAG)
	}

	"DbApi instance" should {

		"get DslContext" in {
			val create = db.jooqContext

			create must not be null

			create
				.select()
				.from(THEME)
				.fetch()
				.isNotEmpty mustBe true
		}

		"create a future for query" in {
			val countFuture: Future[Int] = db.action(create =>
				create
					.fetchCount(THEME)
			)

			await(countFuture) > 1 mustBe true

		}

		"create a record" in {
			val res = db.action(create =>
				create.executeInsert(new NoteRecord()
					.setNote("test note")
					.setThemeId("scala")
				)
			)
			await(res) mustBe 1
		}


	}
}

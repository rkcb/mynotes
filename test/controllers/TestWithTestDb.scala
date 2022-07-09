package controllers

import com.typesafe.config.ConfigFactory
import db.org.jooq.tables.Note.NOTE
import db.org.jooq.tables.NoteTag.NOTE_TAG
import db.org.jooq.tables.Tag.TAG
import models.DbApi
import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Injecting

import java.io.File

class TestWithTestDb extends PlaySpec with GuiceOneAppPerSuite with BeforeAndAfter with Injecting {

	override def fakeApplication(): Application = new GuiceApplicationBuilder()
		.configure(
			Map("db.default.url" -> conf.getString("notes.test.url"))
		)
		.build()

	val conf = ConfigFactory.parseFile(new File("conf/application.conf"))
	lazy val db = app.injector.instanceOf[DbApi]

	def cleanDatabase(): Unit = {
		db.jooqContext.truncate(NOTE).execute()
		db.jooqContext.truncate(NOTE_TAG).execute()
		db.jooqContext.truncate(TAG).execute()
	}




}


package controllers

import com.sun.tools.javac.resources.ct
import models.AppDb
import org.jooq.Tables._
import org.jooq.scalaextensions.Conversions._
import org.scalatest.matchers.must.Matchers.be
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite

import scala.jdk.CollectionConverters._

class DbSpec extends PlaySpec with GuiceOneAppPerSuite {
	lazy val db = app.injector.instanceOf[AppDb]
	import org.jooq.Tables._

 	"AppDb instance" should {
		"get DslContext" in {
			val create = db.jooq
			create must not be null
			create.select().from(THEME).fetch().isNotEmpty mustBe true
		}

	}
}

package  controllers

import com.typesafe.config.ConfigFactory
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import java.io.File

class TestWithTestDb extends PlaySpec with GuiceOneAppPerSuite {

	val conf = ConfigFactory.parseFile(new File("conf/application.conf"))

	override def fakeApplication(): Application = new GuiceApplicationBuilder()
		.configure(
			Map("db.default.url" -> conf.getString("notes.test.url"))
		)
		.build()



}


package models

import org.jooq.impl.DSL
import org.jooq.{DSLContext, SQLDialect}
import play.api.db.Database

import javax.inject.Inject
import scala.concurrent.Future


class DbApi @Inject()(db: Database, ec:  DatabaseExecutionContext) {
  val jooqContext: DSLContext = DSL.using(db.dataSource, SQLDialect.SQLITE)

  def action[A](q: DSLContext => A): Future[A] = {
    Future(q(jooqContext))(ec)
  }

  def url = db.url


}


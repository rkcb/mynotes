package models

import org.jooq.impl.DSL
import org.jooq.{DSLContext, SQLDialect}
import play.api.db.Database

import javax.inject.Inject


class AppDb @Inject()(db: Database) {

  val jooq: DSLContext = DSL.using(db.dataSource, SQLDialect.SQLITE)

}


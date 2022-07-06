import sbt.Keys.libraryDependencies


name := """mynotes"""
organization := "notes"

version := "1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"
Global / onChangedBuildSource := ReloadOnSourceChanges

val jooqV = "3.16.7"

lazy val root = (project in file("."))
	.aggregate(dbmigration)
	.settings(
		jooqVersion := jooqV,
		jooqOrganization := "org.jooq",
		jooqCodegenConfig := file("conf/jooq-codegen.xml")
	)
	.enablePlugins(
		PlayScala,
		JooqCodegenPlugin
	).dependsOn(dbmigration)

lazy val dbmigration = (project in file("dbmigration"))
	.enablePlugins(FlywayPlugin)
	.settings(
		name := "Database Migration",
		flywayUrl := "jdbc:sqlite:app/db/notes.sq3",
		flywayUser := "",
		flywayPassword := "",
		flywayLocations := Seq("database/migration"),
		flywayDriver := "org.sqlite.JDBC",
		Test / flywayUrl := "jdbc:sqlite:app/db/testNotes.sq3",
		Test / flywayLocations := Seq("database/migration"),
		Test / flywayUser := "",
		Test / flywayPassword := "",
		libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
	)

libraryDependencies ++= Seq(
	guice,
	jdbc,
	jdbc % Test,
	"javax.xml.bind" % "jaxb-api" % "2.4.0-b180830.0359",
	"org.xerial" % "sqlite-jdbc" % "3.36.0.3",
	"org.xerial" % "sqlite-jdbc" % "3.36.0.3" % JooqCodegen,
	"org.jooq" %% "jooq-scala" % jooqV,
	"org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "notes.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "notes.binders._"

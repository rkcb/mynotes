import sbt.Keys.libraryDependencies

name := """mynotes"""
organization := "notes"

version := "1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.8"
Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val root = (project in file("."))
  .aggregate(database)
  .settings(
    jooqVersion := "3.16.7",
    jooqOrganization := "org.jooq",
    jooqCodegenConfig := file("conf/jooq-codegen.xml")
  )
  .enablePlugins(
    PlayScala,
    JooqCodegenPlugin
  ).dependsOn(database)

lazy val database = project
  .enablePlugins(FlywayPlugin)
  .settings(
    name := "Database",
    flywayUrl := "jdbc:sqlite:db/notes.sq3",
    flywayUser := "",
    flywayPassword := "",
    flywayDriver := "org.sqlite.JDBC",
    libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
  )



libraryDependencies ++= Seq(
  guice,
  jdbc,
  "javax.xml.bind" % "jaxb-api" % "2.4.0-b180830.0359",
  "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
  "org.xerial" % "sqlite-jdbc" % "3.36.0.3" % JooqCodegen,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "notes.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "notes.binders._"

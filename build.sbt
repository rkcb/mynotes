name := """mynotes"""
organization := "notes"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(
    PlayScala,
    JooqCodegenPlugin
  )

scalaVersion := "2.13.8"
jooqVersion := "3.16.6"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  "javax.xml.bind" % "jaxb-api" % "2.4.0-b180830.0359",
  "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "notes.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "notes.binders._"

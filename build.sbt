ThisBuild / scalaVersion := "2.13.8"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """mynotes""",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
      "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
    )
  )
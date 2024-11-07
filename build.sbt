val scala3Version = "3.5.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Blind_Maze",
    version := "0.1.1-SNAPSHOT",

    scalaVersion := scala3Version,
    mainClass in Compile := Some("de.htwg.se.blindmaze.Main"),

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test
  )

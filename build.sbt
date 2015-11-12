import com.github.play2war.plugin._

name := """web-cntt"""

version := "1.0"

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache,
  "org.mongojack" % "mongojack" % "2.1.0"
)

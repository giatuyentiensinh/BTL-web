resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.8")

addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "1.3-beta3")
// web plugins

libraryDependencies ++= Seq(
  "org.mongojack" % "mongojack" % "2.1.0"
)
organization := "com.typesafe.sbt"

name := "sbt-license-report"

sbtPlugin := true

publishMavenStyle := false
bintrayOrganization := Some("sbt")
name in bintray := "sbt-license-report"
bintrayRepository := "sbt-plugin-releases"


scalariformSettings

versionWithGit

git.baseVersion := "1.0"

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"

scriptedSettings.filterNot(_.key.key.label == libraryDependencies.key.label)

libraryDependencies ++= {
  CrossVersion.binarySbtVersion(scriptedSbt.value) match {
    case "0.13" =>
      Seq(
        "org.scala-sbt" % "scripted-sbt" % scriptedSbt.value % scriptedConf.toString,
        "org.scala-sbt" % "sbt-launch" % scriptedSbt.value % scriptedLaunchConf.toString
      )
    case _ =>
      Seq(
        "org.scala-sbt" %% "scripted-sbt" % scriptedSbt.value % scriptedConf.toString,
        "org.scala-sbt" % "sbt-launch" % scriptedSbt.value % scriptedLaunchConf.toString
      )
  }
}

scriptedLaunchOpts += { "-Dproject.version=" + version.value }

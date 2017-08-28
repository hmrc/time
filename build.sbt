import uk.gov.hmrc.DefaultBuildSettings.targetJvm

enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)

name := "time"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8")
targetJvm := "jvm-1.8"

libraryDependencies ++= AppDependencies()
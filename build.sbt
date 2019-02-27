import uk.gov.hmrc.DefaultBuildSettings.targetJvm

enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)

name := "time"

majorVersion := 3
makePublicallyAvailableOnBintray := true

scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.12.8", "2.11.11")
targetJvm := "jvm-1.8"

libraryDependencies ++= AppDependencies()


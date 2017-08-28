import sbt.Keys._
import sbt._
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  import BuildDependencies._
  import uk.gov.hmrc.DefaultBuildSettings._
  import uk.gov.hmrc.SbtAutoBuildPlugin

  val appName = "time"

  lazy val time = (project in file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      name := "time",
      libraryDependencies ++= Seq(
        Compile.nscalaTime,
        Test.scalaTest,
        Test.pegdown
      ),
      scalaVersion := "2.11.8",
      crossScalaVersions := Seq("2.11.8"),
      developers := List(Developer("duncancrawford", "Duncan Crawford", "duncan.crawford@digital.hmrc.gov.uk", new URL("http://www.hmrc.gov.uk")),
                         Developer("xnejp03", "Petr Nejedly", "petr.nejedly@digital.hmrc.gov.uk", new URL("http://www.hmrc.gov.uk")),
                         Developer("alvarovilaplana", "Alvaro Vilaplana", "alvaro.vilaplana@digital.hmrc.gov.uk", new URL("http://www.hmrc.gov.uk")),
                         Developer("davesammut", "Dave Sammut", "dave.sammut@digital.hmrc.gov.uk", new URL("http://www.hmrc.gov.uk")))
    )
}

private object BuildDependencies {

  object Compile {
    val nscalaTime = "com.github.nscala-time" %% "nscala-time" % "2.16.0"
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.6.0" % scope
  }

  object Test extends Test("test")

}

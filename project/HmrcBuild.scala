import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin

object HmrcBuild extends Build {

  import BuildDependencies._
  import uk.gov.hmrc.DefaultBuildSettings._

  val versionApp = "1.5.0-SNAPSHOT"
  val appName = "time"

  lazy val time = (project in file("."))
    .enablePlugins(SbtAutoBuildPlugin)
    .settings(
      name := "time",
      targetJvm := "jvm-1.7",
      version := versionApp,
      libraryDependencies ++= Seq(
        Compile.nscalaTime,
        Test.scalaTest,
        Test.pegdown
      ),
      crossScalaVersions := Seq("2.11.6"),
      ArtefactDescription()
    )
}

private object BuildDependencies {

  object Compile {
    val nscalaTime = "com.github.nscala-time" %% "nscala-time" % "1.8.0"
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.5.0" % scope
  }

  object Test extends Test("test")

}

object ArtefactDescription {

  def apply() = Seq(
    pomExtra := (<url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git@github.com:hmrc/time.git</connection>
        <developerConnection>scm:git@github.com:hmrc/time.git</developerConnection>
        <url>git@github.com:hmrc/time.git</url>
      </scm>
      <developers>
        <developer>
          <id>duncancrawford</id>
          <name>Duncan Crawford</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>xnejp03</id>
          <name>Petr Nejedly</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>alvarovilaplana</id>
          <name>Alvaro Vilaplana</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>jakobgrunig</id>
          <name>Jakob Grunig</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>vaughansharman</id>
          <name>Vaughan Sharman</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>davesammut</id>
          <name>Dave Sammut</name>
          <url>http://www.equalexperts.com</url>
        </developer>
      </developers>)
    )
}

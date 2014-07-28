import sbt._
import Keys._

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings
  import DefaultBuildSettings._
  import BuildDependencies._
  import uk.gov.hmrc.{SbtBuildInfo, ShellPrompt}

  val versionApp = "1.0.1"

  lazy val time = (project in file("."))
    .settings(version := versionApp)
    .settings(scalaSettings : _*)
    .settings(defaultSettings() : _*)
    .settings(
      targetJvm := "jvm-1.7",
      shellPrompt := ShellPrompt(versionApp),
      libraryDependencies ++= Seq(
        Compile.nscalaTime,
        Test.scalaTest,
        Test.pegdown
      ),
      resolvers := Seq(
        Opts.resolver.sonatypeReleases,
        Opts.resolver.sonatypeSnapshots,
      "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/",
      "typesafe-snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
      ),
      crossScalaVersions := Seq("2.11.2", "2.10.4")
    )
    .settings(SbtBuildInfo(): _*)
    .settings(SonatypeBuild(): _*)

}

private object BuildDependencies {

  object Compile {
    val nscalaTime = "com.github.nscala-time" %% "nscala-time" % "1.2.0"
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "2.2.0" % scope
    val pegdown = "org.pegdown" % "pegdown" % "1.4.2" % scope
  }

  object Test extends Test("test")

  object IntegrationTest extends Test("it")

}

object SonatypeBuild {

  import xerial.sbt.Sonatype._

  def apply() = {
    sonatypeSettings ++ Seq(
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
          <developer>
            <id>ericvlaanderen</id>
            <name>Eric Vlaanderen</name>
            <url>http://www.equalexperts.com</url>
          </developer>
        </developers>)
    )
  }
}


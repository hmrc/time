import sbt._
import Keys._

object HmrcBuild extends Build {

  import uk.gov.hmrc.DefaultBuildSettings
  import BuildDependencies._

  val nameApp = "time"
  val versionApp = "1.0.0-SNAPSHOT"

  val appDependencies = Seq(
    Compile.nscalaTime,

    Test.scalaTest,
    Test.pegdown
  )

  lazy val root = Project(nameApp, file("."), settings = DefaultBuildSettings(nameApp, versionApp, scalaversion = "2.11.1", targetJvm = "jvm-1.7")() ++ Seq(
    libraryDependencies ++= appDependencies, 
    resolvers := Seq(
      Opts.resolver.sonatypeReleases,
      Opts.resolver.sonatypeSnapshots,
      "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/",
      "typesafe-snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
    ),
    crossScalaVersions := Seq("2.11.1", "2.10.4")
  ) ++ SonatypeBuild()
  )

}

private object BuildDependencies {

  object Compile {
    val nscalaTime = "com.github.nscala-time" %% "nscala-time" % "1.2.0" cross CrossVersion.binary
  }

  sealed abstract class Test(scope: String) {
    val scalaTest = "org.scalatest" %% "scalatest" % "2.2.0" % scope cross CrossVersion.binary
    val pegdown = "org.pegdown" % "pegdown" % "1.4.2" % scope cross CrossVersion.Disabled
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


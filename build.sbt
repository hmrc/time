import uk.gov.hmrc.DefaultBuildSettings._

lazy val libName = "time"

lazy val root = Project(libName, file("."))
  .enablePlugins(SbtArtifactory)
  .settings(defaultSettings(): _*)
  .settings(
    majorVersion := 3,
    isPublicArtefact := true
  )
  .settings(
    scalaVersion := "2.13.8",
    crossScalaVersions := Seq("2.11.12", "2.12.15", "2.13.8")
  )

libraryDependencies ++= Seq(
  "com.github.nscala-time"  %% "nscala-time"      % "2.30.0",
  "com.vladsch.flexmark"    % "flexmark-all"      % "0.36.8"  % Test,
  "org.pegdown"             %  "pegdown"          % "1.6.0"   % Test
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor <= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus.play" %% "scalatestplus-play"        % "4.0.3"       % Test,
        "org.scalatestplus"      %% "scalatestplus-scalacheck"  % "3.1.0.0-RC2" % Test
      )
    case _ =>
      libraryDependencies.value ++ Seq(
        "org.scalatestplus.play" %% "scalatestplus-play"        % "5.1.0"       % Test,
        "org.scalatestplus"      %% "scalatestplus-scalacheck"  % "3.1.0.0-RC2" % Test
      )
  }
}

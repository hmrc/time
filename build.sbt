import uk.gov.hmrc.DefaultBuildSettings._

lazy val libName = "time"

lazy val root = Project(libName, file("."))
  .settings(defaultSettings(): _*)
  .settings(
    majorVersion := 3,
    isPublicArtefact := true,
  )
  .settings(
    scalaVersion := "2.13.16",
    crossScalaVersions := Seq("2.13.16", "3.3.5"),
    libraryDependencies ++= Seq(
      "com.github.nscala-time"  %% "nscala-time"      % "3.0.0",
      "com.vladsch.flexmark"    % "flexmark-all"      % "0.64.8"      % Test,
      "org.scalatest"           %% "scalatest"        % "3.2.19"      % Test,
    )
  )

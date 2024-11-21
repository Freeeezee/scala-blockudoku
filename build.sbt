val ver = sys.env.getOrElse("VERSION", "???").stripPrefix("refs/tags/v")
val ci_release = sys.env.getOrElse("CI_RELEASE", "false").toBoolean

ThisBuild / version := ver

ThisBuild / scalaVersion := "3.5.0"

lazy val root = (project in file("."))
  .enablePlugins(JacocoPlugin, AssemblyPlugin)
  .settings(
    assembly / assemblyJarName := s"blockudoku-{ver}.jar",
    coverageEnabled := !ci_release,
    coverageReport := !ci_release,
    name := "blockudoku",
    libraryDependencies ++= Seq(
      "org.jline" % "jline" % "3.26.2",
      "org.scalactic" %% "scalactic" % "3.2.19",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-encoding", "UTF-8"
    ),
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )

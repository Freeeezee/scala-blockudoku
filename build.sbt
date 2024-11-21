val ver = "0.1.0"

ThisBuild / version := ver

ThisBuild / scalaVersion := "3.5.0"

lazy val root = (project in file("."))
  .enablePlugins(JacocoPlugin)
  .settings(
    assembly / assemblyJarName := "blockudoku.jar",
    coverageEnabled := sys.env.get("CI").isEmpty,
    coverageReport := true,
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

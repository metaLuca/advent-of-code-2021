addCommandAlias("fm", "all compile:scalafmt test:scalafmt")
addCommandAlias("cc", "all clean compile")
addCommandAlias("c", "compile")
addCommandAlias("r", "run")
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("ps", "projects")
addCommandAlias("p", "project")

lazy val global = project
  .in(file("."))
  .settings(settings)
  .aggregate(adventofcode)

lazy val adventofcode = project
  .settings(
    name := "adventofcode",
    settings
  )

lazy val settings = Seq(
  scalaVersion := "2.13.7",
  version := "0.1.0-SNAPSHOT",
  scalacOptions ++= scalacSettings,
  resolvers ++= resolversSettings,
  libraryDependencies ++= libsSettings,
  testFrameworks += new TestFramework("munit.Framework"),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
)

lazy val scalacSettings = Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-explaintypes",
  "-opt-warnings",
  "-language:existentials",
  "-language:higherKinds",
  "-opt:l:inline",
  "-opt-inline-from:<source>",
  "-Yrangepos",
  "-Ywarn-numeric-widen",
  "-Ywarn-extra-implicit",
  "-Xlint:_,-type-parameter-shadow,-unused",
  "-Xfatal-warnings"
)

lazy val resolversSettings = Seq(
  Resolver.sonatypeRepo("public"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

lazy val libsSettings = Seq(
  "org.typelevel" %% "cats-core"     % "2.6.1",
  "org.typelevel" %% "cats-effect"   % "3.2.8",
  "org.scalameta" %% "munit"         % "0.7.29" % Test
)

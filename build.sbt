ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "zio-playground",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.18",
      "dev.zio" %% "zio-test" % "2.0.18" % Test,

      "dev.zio" %% "zio-logging" % "2.1.14",

      "dev.zio" %% "zio-http" % "3.0.0-RC2",

      "io.getquill" %% "quill-jdbc-zio" % "4.8.0",
      "org.postgresql" % "postgresql" % "42.5.4"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

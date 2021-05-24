name := """taskAback"""
organization := "example.com"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.12.13"
resolvers += "HMRC-open-artefacts-maven2" at "https://open.artefacts.tax.service.gov.uk/maven2"
//resolvers += Resolver.bintrayRepo("hmrc", "releases")

//libraryDependencies += "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-28" % "0.49.0" % Test


libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.28.2" % Test
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC2"
libraryDependencies += "uk.gov.hmrc.mongo" %% "hmrc-mongo-play-28" % "0.49.0"

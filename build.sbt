name := "scala-memoization"

organization := "com.ted.tools"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "org.scalamacros" % "paradise_2.11.7" % "2.1.0"
libraryDependencies in ThisBuild <+= scalaVersion("org.scala-lang" % "scala-reflect" % _)

addCompilerPlugin("org.scalamacros" % "paradise_2.11.7" % "2.1.0")

resolvers += Resolver.jcenterRepo
bintrayVcsUrl := Some("https://github.com/tudorzgureanu/ScalaMemoization")

name := """provision"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  javaCore,
  javaJpa,
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "org.jadira.usertype" % "usertype.jodatime" % "2.0.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.0.1",
  "be.objectify" %% "deadbolt-java" % "2.4.4"
)



routesGenerator := InjectedRoutesGenerator
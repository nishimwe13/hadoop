name := "hadoop-playgroundupdated"

version := "0.1"

scalaVersion := "2.11.8"

val HadoopVersion = "2.7.7"

libraryDependencies +="org.apache.hadoop" %"hadoop-common" % HadoopVersion
libraryDependencies +="org.apache.hadoop" % "hadoop-hdfs"% HadoopVersion

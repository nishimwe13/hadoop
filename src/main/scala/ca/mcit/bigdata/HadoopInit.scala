package ca.mcit.bigdata

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}

object HadoopInit extends  App {

  //1. A configuration object
  //the default value of fs.default is the local file system == file://
  val conf= new Configuration()
  //val hadoopConfDir = "/home/leila/opt/hadoop-2.7.7/etc/cloudera"
  //val hadoopConfDir = sys.env("HADOOP_CONF_DIR")
  val hadoopConfDir = "/home/leila/Desktop/HADOOP_CONFIG"
  conf.addResource(new Path(s"$hadoopConfDir/core-site.xml"))
  conf.addResource(new Path(s"$hadoopConfDir/hdfs-site.xml"))

  //2.create the client
  //the orange box has been created
  val fs = FileSystem.get(conf)
  println(fs.getUri)
  println(fs.getWorkingDirectory)

  //3.send a test command 'hadoop fs -ls /'
  val contentOfRoot: Array[FileStatus]= fs.listStatus(new Path("/"))
  contentOfRoot.map(_.getPath).foreach(println)

  //4.Close HDFS client
  fs.close()

}

package ca.mcit.bigdata

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}

trait HadoopClient {

  val conf= new Configuration()
  //val hadoopConfDir = sys.env("HADOOP_CONF_DIR")
  val hadoopConfDir = "/home/leila/Desktop/HADOOP_CONFIG"
  conf.addResource(new Path(s"$hadoopConfDir/core-site.xml"))
  conf.addResource(new Path(s"$hadoopConfDir/hdfs-site.xml"))


  //because it is a public variable.. it is gonna b used outside
  //that's why we have to add the annotation
  val fs:FileSystem = FileSystem.get(conf)
}

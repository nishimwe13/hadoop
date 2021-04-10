package ca.mcit.bigdata

import org.apache.hadoop.fs.Path

object Hadoop03Directories extends App with HadoopClient {

  //note that env variable is per run!
  fs.listStatus(new Path("/user")).map(_.getPath).foreach(println)
  fs.close()
}

package ca.mcit.bigdata

import org.apache.hadoop.fs.Path

object Hadoop02CopyFromLocal extends App with HadoopClient {

  //hadoop fs copyFromLocal local/file to remote/path
  fs.copyFromLocalFile(new Path("/home/leila/IdeaProjects/hadoop-playgroundupdated/data/movie.csv"),new Path("/user/bdsf2001/leila"))
  fs.close()
}

package ca.mcit.bigdata

import org.apache.hadoop.fs.{FSDataOutputStream, Path}

object Hadoop04Writer extends App with HadoopClient {

  val filePath = new Path("/user/bdsf2001/leila/Hadoop04Writer.txt")
  //val filePath = new Path("/tmp/Hadoop04Writer.txt")
  //val filePath = new Path("/user/bdsf2001/leila/stm")
  //fs.mkdirs(filePath)

  val tutorialList= List("HadoopInit","Hadoop02CopyFromLocal","Hadoop03Directory","Hadoop04Writer")

  val writer: FSDataOutputStream = if(fs.exists(filePath)) fs.append(filePath) else fs.create(filePath)

  tutorialList
    .map(tutorial => s"$tutorial\n")
    .foreach(tutorial => writer.writeChars(tutorial))

  writer.flush()
  writer.close()
  fs.close()


}

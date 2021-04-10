package ca.mcit.bigdata

case class Movie(movieId:Int, title:String, year:Int, director:String)

object Movie{
  def apply(csv:String): Movie ={
    val fields: Array[String] = csv.split(",",-1)
    Movie(fields(0).toInt,fields(1),fields(2).toInt, fields(3))
  }

  def toCsv(movie: Option[Movie]):String={
    movie match {
      case Some(m) => s"${m.title},${m.year},${m.director}"
      case None => ",,"
    }
  }
}

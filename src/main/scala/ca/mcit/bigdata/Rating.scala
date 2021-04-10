package ca.mcit.bigdata

case class Rating(rId:Int,mId:Int,stars:Int,ratingDate:Option[String])

object Rating{
  def apply(csv:String): Rating ={
    val fields: Array[String]=csv.split(",",-1)
    val ratingDate = if (fields.size == 3) None else Option(fields(3))
    Rating(fields(0).toInt,fields(1).toInt,fields(2).toInt,ratingDate)
  }

  def toCsv(rating: Rating):String ={
    s"${rating.rId},${rating.mId},${rating.stars},${rating.ratingDate.getOrElse("")}"
  }
}
package ca.mcit.bigdata

case class EnrichedRating(rating:Rating,movie: Option[Movie])

object EnrichedRating{

  //Returns CSV representation of this object
  def toCsv(enrichedRating: EnrichedRating):String ={
    s"${Rating.toCsv(enrichedRating.rating)},${Movie.toCsv(enrichedRating.movie)}"
  }
}
package ca.mcit.bigdata

import org.apache.hadoop.fs.{FSDataOutputStream, Path}

import scala.io.Source

object Hadoop05Enricher extends App with HadoopClient {

  /**
   * we have movie and rating , we'd like to get the average rating of each movie
   * we need to enrich rating with the movie information to calculate average rating of each title
   *
   * here we have steps:
   * 1.Read(Extract)
   * 1.a. data model
   * 1.b.read files(from local or remote)
   */
  val movieSource = Source.fromFile("/home/leila/IdeaProjects/hadoop-playgroundupdated/data/movie.csv")
  val movies = movieSource.getLines().toList.tail.map(Movie(_))
  movieSource.close()

  val ratingSource = Source.fromFile("/home/leila/IdeaProjects/hadoop-playgroundupdated/data/rating.csv")
  val ratings = ratingSource.getLines().toList.tail.map(Rating(_))
  ratingSource.close()

  /**
   * 2.Enrich
   * 2.a. in memory lookup table of movies
   * 2.b. enrich rating with the lookup table
   * 2.c. calculate average rating
   */
  val movieLookUpTable = movies.map(movie =>movie.movieId -> movie).toMap
  val enrichedRatings = ratings.map{rating =>
    val movie: Option[Movie] = movieLookUpTable.get(rating.mId)
    EnrichedRating(rating,movie)
  }

  /**
   * 3.Write the result on HDFS(load)
   * we don't keep debug code in the code base otherwise we gonna loose mark!!!!!
   *
   *enrichedRatings.map(EnrichedRating.toCsv)foreach(println)
  */

  val writer:FSDataOutputStream = fs.create(new Path("/user/bdsf2001/leila/enriched_rating.csv"))

  enrichedRatings
    .map(EnrichedRating.toCsv)
    .map(enrichedRating => s"$enrichedRating\n")
    .foreach(enrichedRating => writer.writeChars(enrichedRating))
  writer.flush()
  writer.close()

  fs.close()

}

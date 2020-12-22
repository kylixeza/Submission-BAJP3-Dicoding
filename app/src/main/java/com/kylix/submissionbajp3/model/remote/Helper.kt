package com.kylix.submissionbajp3.model.remote

import android.app.Application
import com.kylix.submissionbajp3.model.remote.response.MovieModelResponse
import com.kylix.submissionbajp3.model.remote.response.TvShowModelResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Helper(private val application: Application) {
    private fun parsingDataToString(data: String): String? {
        return try {
            val `is` = application.assets.open(data)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun getMovieList(): List<MovieModelResponse> {
        val listMovies = ArrayList<MovieModelResponse>()
        try {
            val responseObject = JSONObject(parsingDataToString("movies.json"))
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val movieId = movie.getInt("movieId")
                val movieTitle = movie.getString("movieTitle")
                val movieDescription = movie.getString("movieDescription")
                val movieRelease = movie.getString("movieRelease")
                val movieGenre = movie.getString("movieGenre")
                val movieDuration = movie.getString("movieDuration")
                val movieRating = movie.getString("movieRating")
                val moviePoster = movie.getString("moviePoster")

                val movieResponse = MovieModelResponse(
                    movieId,
                    movieTitle,
                    movieDescription,
                    movieRelease,
                    movieGenre,
                    movieDuration,
                    movieRating,
                    moviePoster
                )
                listMovies.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.localizedMessage
        }
        return listMovies
    }

    fun getMovieById(id: Int): MovieModelResponse {
        val movieData = String.format("movie_%s.json", id)
        var movie = MovieModelResponse()
        try {
            val result = parsingDataToString(movieData)
            if (result != null) {
                val responseObject = JSONObject(result)
                val movieId = responseObject.getInt("movieId")
                val movieTitle = responseObject.getString("movieTitle")
                val movieDescription = responseObject.getString("movieDescription")
                val movieRelease = responseObject.getString("movieRelease")
                val movieGenre = responseObject.getString("movieGenre")
                val movieDuration = responseObject.getString("movieDuration")
                val movieRating = responseObject.getString("movieRating")
                val moviePoster = responseObject.getString("moviePoster")
                movie = MovieModelResponse(
                    movieId = movieId,
                    movieTitle = movieTitle,
                    movieDescription = movieDescription,
                    movieRelease = movieRelease,
                    movieGenre = movieGenre,
                    movieDuration = movieDuration,
                    movieRating = movieRating,
                    moviePoster = moviePoster
                )
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movie
    }

    fun getTvShowList():List<TvShowModelResponse>{
        val listTvShow = ArrayList<TvShowModelResponse>()

        try {
            val responseObject = JSONObject(parsingDataToString("tvshows.json"))
            val listArray = responseObject.getJSONArray("result")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val tvShowId = tvShow.getInt("tvShowId")
                val tvShowTitle = tvShow.getString("tvShowTitle")
                val tvShowDescription = tvShow.getString("tvShowDescription")
                val tvShowRelease = tvShow.getString("tvShowRelease")
                val tvShowGenre = tvShow.getString("tvShowGenre")
                val tvShowDuration = tvShow.getString("tvShowDuration")
                val tvShowRating = tvShow.getString("tvShowRating")
                val tvShowPoster = tvShow.getString("tvShowPoster")
                val courseResponse = TvShowModelResponse(
                    tvShowId = tvShowId,
                    tvShowTitle = tvShowTitle,
                    tvShowDescription = tvShowDescription,
                    tvShowRelease = tvShowRelease,
                    tvShowGenre = tvShowGenre,
                    tvShowDuration = tvShowDuration,
                    tvShowRating = tvShowRating,
                    tvShowPoster = tvShowPoster
                )
                listTvShow.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listTvShow
    }

    fun getTvShowById(id: Int): TvShowModelResponse {
        val tvShowData = String.format("tvshow_%s.json", id)
        var tvShow = TvShowModelResponse()
        try {
            val result = parsingDataToString(tvShowData)
            if (result != null) {
                val responseObject = JSONObject(result)
                val tvShowId = responseObject.getInt("tvShowId")
                val tvShowTitle = responseObject.getString("tvShowTitle")
                val tvShowDescription = responseObject.getString("tvShowDescription")
                val tvShowRelease = responseObject.getString("tvShowRelease")
                val tvShowGenre = responseObject.getString("tvShowGenre")
                val tvShowDuration = responseObject.getString("tvShowDuration")
                val tvShowRating = responseObject.getString("tvShowRating")
                val tvShowPoster = responseObject.getString("tvShowPoster")
                tvShow = TvShowModelResponse(
                    tvShowId = tvShowId,
                    tvShowTitle = tvShowTitle,
                    tvShowDescription = tvShowDescription,
                    tvShowRelease = tvShowRelease,
                    tvShowGenre = tvShowGenre,
                    tvShowDuration = tvShowDuration,
                    tvShowRating = tvShowRating,
                    tvShowPoster = tvShowPoster
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return tvShow
    }
}
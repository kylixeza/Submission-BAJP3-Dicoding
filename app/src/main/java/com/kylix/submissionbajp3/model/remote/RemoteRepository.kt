package com.kylix.submissionbajp3.model.remote

import android.os.Handler
import android.os.Looper
import com.kylix.submissionbajp3.model.remote.response.MovieModelResponse
import com.kylix.submissionbajp3.model.remote.response.TvShowModelResponse
import com.kylix.submissionbajp3.utils.EspressoIdlingResource

class RemoteRepository(private val helper: Helper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private var INSTANCE: RemoteRepository? = null
        private const val TIME_IN_MILLIS: Long = 1500

        fun getInstance(helper: Helper): RemoteRepository? {
            if (INSTANCE == null)
                INSTANCE = RemoteRepository(helper)
            return INSTANCE
        }
    }

    interface GetMovieListCallback {
        fun onMovieReceived(movieModelResponseList: List<MovieModelResponse>)
    }

    interface GetMovieByIdCallback {
        fun onMovieReceived(movieModelResponse: MovieModelResponse)
    }

    interface GetTvShowListCallback {
        fun onTvShowReceived(tvShowModelResponseList: List<TvShowModelResponse>)
    }

    interface GetTvShowByIdCallback {
        fun onTvShowReceived(tvShowModelResponse: TvShowModelResponse)
    }

    fun getMovieList(getMovieListCallback: GetMovieListCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            getMovieListCallback.onMovieReceived(helper.getMovieList())
            EspressoIdlingResource.decrement()
        }, TIME_IN_MILLIS)
    }

    fun getMovieById(movieId: Int?, getMovieByIdCallback: GetMovieByIdCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            movieId?.let { helper.getMovieById(it) }?.let { getMovieByIdCallback.onMovieReceived(it) }
            EspressoIdlingResource.decrement()
        }, TIME_IN_MILLIS)
    }

    fun getTvShowList(getTvShowListCallback: GetTvShowListCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            getTvShowListCallback.onTvShowReceived(helper.getTvShowList())
            EspressoIdlingResource.decrement()
        }, TIME_IN_MILLIS)
    }

    fun getTvShowById(tvShowId: Int?, getTvShowByIdCallback: GetTvShowByIdCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            tvShowId?.let { helper.getTvShowById(it) }?.let { getTvShowByIdCallback.onTvShowReceived(it) }
            EspressoIdlingResource.decrement()
        }, TIME_IN_MILLIS)
    }
}
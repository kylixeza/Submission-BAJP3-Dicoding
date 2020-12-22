package com.kylix.submissionbajp3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kylix.submissionbajp3.model.DataSource
import com.kylix.submissionbajp3.model.NetworkBoundResource
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.model.remote.APIResponse
import com.kylix.submissionbajp3.model.remote.RemoteRepository
import com.kylix.submissionbajp3.model.remote.response.MovieModelResponse
import com.kylix.submissionbajp3.model.remote.response.TvShowModelResponse
import com.kylix.submissionbajp3.model.repository.LocalRepository
import com.kylix.submissionbajp3.utils.AppExecutors
import com.kylix.submissionbajp3.utils.Resource
import java.util.ArrayList

open class FakeDataRepository (
    private val localRepository: LocalRepository,
    private val remoteRepositoryJava: RemoteRepository,
    val executors: AppExecutors
): DataSource {
    override fun getMovies(): LiveData<Resource<List<MovieModel>>> {
        return object : NetworkBoundResource<List<MovieModel>, List<MovieModelResponse>>(executors) {

            override fun loadDataFromDB(): LiveData<List<MovieModel>> {
                return localRepository.getMovies()
            }

            override fun shouldFetch(data: List<MovieModel>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<MovieModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieModel>> {
        return object : NetworkBoundResource<MovieModel, MovieModelResponse>(executors) {

            override fun loadDataFromDB(): LiveData<MovieModel> {
                return localRepository.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieModel): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<MovieModelResponse>>? {
                return null
            }

            override fun saveCallResult(data: MovieModelResponse) {

            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieModel, isFavorite: Boolean) {
        val runnable = { localRepository.setFavoriteMovie(movie, isFavorite) }
        executors.diskIO().execute(runnable)
    }

    override fun insertMovies(movies: List<MovieModel>) {
        val runnable = {
            if (localRepository.getMovies().value.isNullOrEmpty()) {
                localRepository.insertMovies(movies)
            }
        }
        executors.diskIO().execute(runnable)
    }

    override fun getMovieAsPaged(): LiveData<Resource<PagedList<MovieModel>>> {
        return object : NetworkBoundResource<PagedList<MovieModel>, List<MovieModelResponse>>(executors) {

            override fun loadDataFromDB(): LiveData<PagedList<MovieModel>> {
                return LivePagedListBuilder(
                    localRepository.getMovieAsPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieModel>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<MovieModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowModel>>> {
        return object : NetworkBoundResource<List<TvShowModel>, List<TvShowModelResponse>>(executors) {

            override fun loadDataFromDB(): LiveData<List<TvShowModel>> {
                return localRepository.getTvShows()
            }

            override fun shouldFetch(data: List<TvShowModel>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<TvShowModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<TvShowModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowModel>> {
        return object : NetworkBoundResource<TvShowModel, TvShowModelResponse>(executors) {

            override fun loadDataFromDB(): LiveData<TvShowModel> {
                return localRepository.getTvShowById(tvShowId)
            }

            override fun shouldFetch(data: TvShowModel): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<TvShowModelResponse>>? {
                return null
            }

            override fun saveCallResult(data: TvShowModelResponse) {

            }
        }.asLiveData()
    }

    override fun setFavoriteTvShow(tvShow: TvShowModel, isFavorite: Boolean) {
        val runnable = { localRepository.setFavoriteTvShow(tvShow, isFavorite) }
        executors.diskIO().execute(runnable)
    }

    override fun insertTvShows(tvShows: List<TvShowModel>) {
        val runnable = {
            if (localRepository.getTvShows().value.isNullOrEmpty()) {
                localRepository.insertTvShows(tvShows)
            }
        }
        executors.diskIO().execute(runnable)
    }

    override fun getTvShowAsPaged(): LiveData<Resource<PagedList<TvShowModel>>> {
        return object : NetworkBoundResource<PagedList<TvShowModel>, List<TvShowModelResponse>>(executors) {

            override fun loadDataFromDB(): LiveData<PagedList<TvShowModel>> {
                return LivePagedListBuilder(
                    localRepository.getTvShowAsPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvShowModel>): Boolean? {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<TvShowModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<TvShowModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getMovieList(): LiveData<List<MovieModel>> {
        val moviesMutable = MutableLiveData<List<MovieModel>>()

        remoteRepositoryJava.getMovieList(object : RemoteRepository.GetMovieListCallback {
            override fun onMovieReceived(movieModelResponseList: List<MovieModelResponse>) {
                val movies = ArrayList<MovieModel>()
                for (i in movieModelResponseList.indices) {
                    val response = movieModelResponseList[i]
                    val movie = MovieModel(
                        movieId = response.movieId,
                        movieTitle = response.movieTitle,
                        movieDescription = response.movieDescription,
                        movieRelease = response.movieRelease,
                        movieGenre = response.movieGenre,
                        movieDuration = response.movieDuration,
                        movieRating = response.movieRating,
                        moviePoster = response.moviePoster
                    )
                    movies.add(movie)
                }
                moviesMutable.postValue(movies)
            }

        })
        return moviesMutable
    }

    override fun getMovieById(movieId: Int): LiveData<MovieModel> {
        val moviesMutable = MutableLiveData<MovieModel>()

        remoteRepositoryJava.getMovieById(movieId, object : RemoteRepository.GetMovieByIdCallback {
            override fun onMovieReceived(movieModelResponse: MovieModelResponse) {
                val movie = MovieModel(
                    movieId = movieModelResponse.movieId,
                    movieTitle = movieModelResponse.movieTitle,
                    movieDescription = movieModelResponse.movieDescription,
                    movieRelease = movieModelResponse.movieRelease,
                    movieGenre = movieModelResponse.movieGenre,
                    movieDuration = movieModelResponse.movieDuration,
                    movieRating = movieModelResponse.movieRating,
                    moviePoster = movieModelResponse.moviePoster
                )
                moviesMutable.postValue(movie)
            }


        })
        return moviesMutable

    }

    override fun getTvShowList(): LiveData<List<TvShowModel>> {
        val tvShowsMutable = MutableLiveData<List<TvShowModel>>()

        remoteRepositoryJava.getTvShowList(object : RemoteRepository.GetTvShowListCallback {
            override fun onTvShowReceived(tvShowModelResponseList: List<TvShowModelResponse>) {
                val tvShows = ArrayList<TvShowModel>()
                for (i in tvShowModelResponseList.indices) {
                    val response = tvShowModelResponseList[i]
                    val tvShow = TvShowModel(
                        tvShowId = response.tvShowId,
                        tvShowTitle = response.tvShowTitle,
                        tvShowDescription = response.tvShowDescription,
                        tvShowRelease = response.tvShowRelease,
                        tvShowGenre = response.tvShowGenre,
                        tvShowDuration = response.tvShowDuration,
                        tvShowRating = response.tvShowRating,
                        tvShowPoster = response.tvShowPoster
                    )
                    tvShows.add(tvShow)
                }
                tvShowsMutable.postValue(tvShows)
            }
        })
        return tvShowsMutable
    }

    override fun getTvShowById(tvShowId: Int): LiveData<TvShowModel> {
        val tvShowsMutable = MutableLiveData<TvShowModel>()

        remoteRepositoryJava.getTvShowById(tvShowId, object : RemoteRepository.GetTvShowByIdCallback {
            override fun onTvShowReceived(tvShowModelResponse: TvShowModelResponse) {
                val tvShow = TvShowModel(
                    tvShowId = tvShowModelResponse.tvShowId,
                    tvShowTitle = tvShowModelResponse.tvShowTitle,
                    tvShowDescription = tvShowModelResponse.tvShowDescription,
                    tvShowRelease = tvShowModelResponse.tvShowRelease,
                    tvShowGenre = tvShowModelResponse.tvShowGenre,
                    tvShowDuration = tvShowModelResponse.tvShowDuration,
                    tvShowRating = tvShowModelResponse.tvShowRating,
                    tvShowPoster = tvShowModelResponse.tvShowPoster
                )
                tvShowsMutable.postValue(tvShow)
            }
        })
        return tvShowsMutable
    }
}
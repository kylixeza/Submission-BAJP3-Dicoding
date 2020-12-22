package com.kylix.submissionbajp3.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.utils.Resource

interface DataSource {
    fun getMovieList(): LiveData<List<MovieModel>>
    fun getMovieById(movieId: Int): LiveData<MovieModel>
    fun getTvShowList(): LiveData<List<TvShowModel>>
    fun getTvShowById(tvShowId: Int): LiveData<TvShowModel>

    fun getMovies(): LiveData<Resource<List<MovieModel>>>?
    fun getMovie(movieId: Int): LiveData<Resource<MovieModel>>?
    fun setFavoriteMovie(movie: MovieModel, isFavorite: Boolean)
    fun insertMovies(movies: List<MovieModel>)
    fun getMovieAsPaged(): LiveData<Resource<PagedList<MovieModel>>>

    fun getTvShows(): LiveData<Resource<List<TvShowModel>>>?
    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowModel>>?
    fun setFavoriteTvShow(tvShow: TvShowModel, isFavorite: Boolean)
    fun insertTvShows(tvShows: List<TvShowModel>)
    fun getTvShowAsPaged(): LiveData<Resource<PagedList<TvShowModel>>>
}
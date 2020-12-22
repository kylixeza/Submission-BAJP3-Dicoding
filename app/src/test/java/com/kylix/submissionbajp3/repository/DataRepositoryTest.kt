package com.kylix.submissionbajp3.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.model.remote.RemoteRepository
import com.kylix.submissionbajp3.model.repository.LocalRepository
import com.kylix.submissionbajp3.utils.FakeDummy
import com.kylix.submissionbajp3.utils.InstantAppExecutors
import com.kylix.submissionbajp3.utils.LiveDataTestUtil
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class DataRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val localRepository = mock(LocalRepository::class.java)
    private val remoteRepositoryJava = mock(RemoteRepository::class.java)
    private val appExecutors = mock(InstantAppExecutors::class.java)
    private val dataRepository = FakeDataRepository(localRepository, remoteRepositoryJava, appExecutors)

    private val movieList = FakeDummy.getRemoteMovie()
    private val movieId = movieList[0].movieId
    private val movieData = movieId?.let { FakeDummy.getRemoteMovieById(it) }

    private val tvShowList = FakeDummy.getRemoteTvShow()
    private val tvShowId = tvShowList[0].tvShowId
    private val tvShowData = tvShowId?.let { FakeDummy.getRemoteTvShowById(it) }

    private fun <T> anyOfGeneric(type: Class<T>): T = any(type)
    private fun <T> eqOfGeneric(obj: T): T = eq(obj)

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        `when`(localRepository.getMovieAsPaged()).thenReturn(dataSourceFactory as DataSource.Factory<Int, MovieModel>)
        dataRepository.getMovieAsPaged()
        verify(localRepository).getMovieAsPaged()
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieModel>()
        movie.value = movieId?.let { FakeDummy.getMovieById(it) }
        `when`(movieId?.let { localRepository.getMovieById(it) }).thenReturn(movie)
        val result = movieId?.let { dataRepository.getMovie(it) }?.let { LiveDataTestUtil.getValue(it) }
        movieId?.let { verify(localRepository).getMovieById(it) }
        assertNotNull(result)
    }


    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        `when`(localRepository.getTvShowAsPaged()).thenReturn(dataSourceFactory as DataSource.Factory<Int, TvShowModel>)
        dataRepository.getTvShowAsPaged()
        verify(localRepository).getTvShowAsPaged()
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowModel>()
        tvShow.value = tvShowId?.let { FakeDummy.getTvShowById(it) }
        `when`(tvShowId?.let { localRepository.getTvShowById(it) }).thenReturn(tvShow)
        val result = tvShowId?.let { dataRepository.getTvShow(it) }?.let { LiveDataTestUtil.getValue(it) }
        tvShowId?.let { verify(localRepository).getTvShowById(it) }
        assertNotNull(result)
    }

    @Test
    fun getMovieList() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetMovieListCallback
            callback.onMovieReceived(movieList)
            null
        }.`when`(remoteRepositoryJava).getMovieList(anyOfGeneric(RemoteRepository.GetMovieListCallback::class.java))
        val result = LiveDataTestUtil.getValue(dataRepository.getMovieList())
        verify(
            remoteRepositoryJava,
            times(1)
        ).getMovieList(anyOfGeneric(RemoteRepository.GetMovieListCallback::class.java))
        assertEquals(movieList.size, result.size)
        assertNotNull(result)
    }

    @Test
    fun getMovieById() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepository.GetMovieByIdCallback
            movieData?.let { it1 -> callback.onMovieReceived(it1) }
            null
        }.`when`(remoteRepositoryJava).getMovieById(
            eqOfGeneric(movieId),
            anyOfGeneric(RemoteRepository.GetMovieByIdCallback::class.java)
        )
        val result = movieId?.let { dataRepository.getMovieById(it) }?.let { LiveDataTestUtil.getValue(it) }
        verify(
            remoteRepositoryJava,
            times(1)
        ).getMovieById(
            eqOfGeneric(movieId),
           anyOfGeneric(RemoteRepository.GetMovieByIdCallback::class.java)
        )
        assertEquals(movieData?.movieId, result?.movieId)
    }

    @Test
    fun getTvShowList() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetTvShowListCallback
            callback.onTvShowReceived(tvShowList)
            null
        }.`when`(remoteRepositoryJava)
            .getTvShowList(anyOfGeneric(RemoteRepository.GetTvShowListCallback::class.java))
        val result = LiveDataTestUtil.getValue(dataRepository.getTvShowList())
        verify(
            remoteRepositoryJava,
            times(1)
        ).getTvShowList(anyOfGeneric(RemoteRepository.GetTvShowListCallback::class.java))
        assertEquals(tvShowList.size, result.size)
    }

    @Test
    fun getTvShowById() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepository.GetTvShowByIdCallback
            tvShowData?.let { it1 -> callback.onTvShowReceived(it1) }
            null
        }.`when`(remoteRepositoryJava).getTvShowById(
            eqOfGeneric(tvShowId),
            anyOfGeneric(RemoteRepository.GetTvShowByIdCallback::class.java)
        )
        val result = tvShowId?.let { dataRepository.getTvShowById(it) }?.let { LiveDataTestUtil.getValue(it) }
        verify(
            remoteRepositoryJava,
            times(1)
        ).getTvShowById(eqOfGeneric(tvShowId), anyOfGeneric(RemoteRepository.GetTvShowByIdCallback::class.java))
        assertEquals(tvShowData?.tvShowId, result?.tvShowId)
    }
}
package com.kylix.submissionbajp3.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kylix.submissionbajp3.model.local.entity.MovieModel
import com.kylix.submissionbajp3.model.repository.DataRepository
import com.kylix.submissionbajp3.ui.movie.MovieViewModel
import com.kylix.submissionbajp3.utils.FakeDummy
import com.kylix.submissionbajp3.utils.LiveDataTestUtil
import com.kylix.submissionbajp3.utils.Resource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@Suppress("UNCHECKED_CAST")
class MovieViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: MovieViewModel? = null
    private val dataRepository = Mockito.mock(DataRepository::class.java)
    private var data: MovieModel? = null

    @Before
    fun setUp() {
        viewModel = MovieViewModel(dataRepository)
        data = MovieModel(
            1,
            "Alita: Battle Angle",
            "When Alita awakens with no memory of who she is in a future " +
                "world she does not recognize, she is taken in by Ido, a compassionate doctor " +
                "who realizes that somewhere in this abandoned cyborg shell is the heart and" +
                " soul of a young woman with an extraordinary past.",
            "14/02/2019",
            "Action, Science Fiction, Adventure",
            "2 hours 2 minutes",
            "71%",
            "movie_alita"
        )
    }

    @Test
    fun getMovies() {
        val moviesMutable = MutableLiveData<List<MovieModel>>()
        moviesMutable.value = FakeDummy.getMovie()
        `when`(dataRepository.getMovieList()).thenReturn(moviesMutable)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.movies?.observeForever(observer as Observer<List<MovieModel>>)
        Mockito.verify(dataRepository).getMovieList()
    }

    @Test
    fun movieDetail() {
        val moviesMutable = MutableLiveData<MovieModel>()
        moviesMutable.value = data?.movieId?.let { FakeDummy.getMovieById(it) }
        `when`(data?.movieId?.let { dataRepository.getMovieById(it) }).thenReturn(moviesMutable)
        val observer = Mockito.mock(Observer::class.java)
        data?.movieId?.let { viewModel?.movieDetail(it)?.observeForever(observer as Observer<MovieModel>) }
        data?.movieId?.let { Mockito.verify(dataRepository).getMovieById(it) }
        assertEquals(data?.movieId, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieId })
        assertEquals(data?.movieTitle, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieTitle })
        assertEquals(data?.movieDescription, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieDescription })
        assertEquals(data?.movieRelease, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieRelease })
        assertEquals(data?.movieGenre, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieGenre })
        assertEquals(data?.movieDuration, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieDuration })
        assertEquals(data?.movieRating, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.movieRating })
        assertEquals(data?.moviePoster, data?.movieId?.let { viewModel?.movieDetail(it)?.value?.moviePoster })
    }

    @Test
    fun getMovie(){
        val movie = MutableLiveData<Resource<MovieModel>>()
        movie.value = Resource.success(this.data?.movieId?.let { FakeDummy.getMovieById(it) })
        `when`(this.data?.movieId?.let { dataRepository.getMovie(it) }).thenReturn(movie)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getMovie?.observeForever(observer as Observer<Resource<MovieModel>>)
        val result = LiveDataTestUtil.getValue(dataRepository.getMovie(this.data?.movieId!!))
        Assert.assertNotNull(result)
    }
}
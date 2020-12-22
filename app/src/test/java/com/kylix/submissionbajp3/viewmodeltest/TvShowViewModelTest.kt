package com.kylix.submissionbajp3.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.model.repository.DataRepository
import com.kylix.submissionbajp3.ui.tvshow.TvShowViewModel
import com.kylix.submissionbajp3.utils.FakeDummy
import com.kylix.submissionbajp3.utils.LiveDataTestUtil
import com.kylix.submissionbajp3.utils.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@Suppress("UNCHECKED_CAST")
class TvShowViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowViewModel? = null
    private val dataRepository = Mockito.mock(DataRepository::class.java)
    private var data: TvShowModel? = null

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(dataRepository)
        data = TvShowModel(
            1,
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan " +
                "discovered a strange boy whom he named Goku. Gohan raised him and trained" +
                " Goku in martial arts until he died. The young and very strong boy was on " +
                "his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, " +
                "whose search for the dragon balls brought her to Goku's home. Together, " +
                "they set off to find all seven dragon balls in an adventure that would change" +
                " Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, " +
                "Krillin, Master Roshi and more. And see his adventures as a boy, all leading " +
                "up to Dragonball Z and later Dragonball GT.\n",
            "02/26/1986",
            "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
            "25 minutes / episode",
            "80%",
            "tvshow_dragon_ball"
        )
    }

    @Test
    fun getTvShows() {
        val tvShowsMutable = MutableLiveData<List<TvShowModel>>()
        tvShowsMutable.value = FakeDummy.getTvShow()
        `when`(dataRepository.getTvShowList()).thenReturn(tvShowsMutable)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.tvShow?.observeForever(observer as Observer<List<TvShowModel>>)
        Mockito.verify(dataRepository).getTvShowList()
    }

    @Test
    fun tvShowDetail() {
        val tvShowMutable = MutableLiveData<TvShowModel>()
        tvShowMutable.value = data?.tvShowId?.let { FakeDummy.getTvShowById(it) }
        `when`(data?.tvShowId?.let { dataRepository.getTvShowById(it) }).thenReturn(tvShowMutable)
        val observer = Mockito.mock(Observer::class.java)
        data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.observeForever(observer as Observer<TvShowModel>) }
        data?.tvShowId?.let { Mockito.verify(dataRepository).getTvShowById(it) }
        assertEquals(data?.tvShowId, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowId })
        assertEquals(data?.tvShowTitle, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowTitle })
        assertEquals(data?.tvShowDescription, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowDescription })
        assertEquals(data?.tvShowRelease, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowRelease })
        assertEquals(data?.tvShowGenre, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowGenre })
        assertEquals(data?.tvShowDuration, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowDuration })
        assertEquals(data?.tvShowRating, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowRating })
        assertEquals(data?.tvShowPoster, data?.tvShowId?.let { viewModel?.tvShowDetail(it)?.value?.tvShowPoster })
    }

    @Test
    fun getTvShow(){
        val tvShow = MutableLiveData<Resource<TvShowModel>>()
        tvShow.value = Resource.success(this.data?.tvShowId?.let { FakeDummy.getTvShowById(it) })
        `when`(this.data?.tvShowId?.let { dataRepository.getTvShow(it) }).thenReturn(tvShow)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getTvShow?.observeForever(observer as Observer<Resource<TvShowModel>>)
        val result = LiveDataTestUtil.getValue(dataRepository.getTvShow(this.data?.tvShowId!!))
        assertNotNull(result)
    }
}
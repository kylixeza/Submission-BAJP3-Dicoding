package com.kylix.submissionbajp3.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kylix.submissionbajp3.model.local.entity.TvShowModel
import com.kylix.submissionbajp3.model.repository.DataRepository
import com.kylix.submissionbajp3.utils.Resource

class TvShowViewModel(private val dataRepository: DataRepository): ViewModel() {
    val tvShow : LiveData<List<TvShowModel>> = dataRepository.getTvShowList()
    fun tvShowDetail (id : Int): LiveData<TvShowModel> = dataRepository.getTvShowById(id)

    fun insertTvShows(tvShows: List<TvShowModel>){
        dataRepository.insertTvShows(tvShows)
    }

    val getTvShows: LiveData<Resource<List<TvShowModel>>> = dataRepository.getTvShows()

    val getTvShowPaged: LiveData<Resource<PagedList<TvShowModel>>> = dataRepository.getTvShowAsPaged()

    fun setFavoriteTvShow() {
        getTvShow.value?.data?.let {
            val newState = !it.favorite
            dataRepository.setFavoriteTvShow(it, newState)
        }
    }

    val tvShowId = MutableLiveData<Int>()

    val getTvShow: LiveData<Resource<TvShowModel>> = Transformations.switchMap(tvShowId) { tvShowId ->
        dataRepository.getTvShow(tvShowId)
    }
}
package com.kylix.submissionbajp3.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kylix.submissionbajp3.di.Injection
import com.kylix.submissionbajp3.model.repository.DataRepository
import com.kylix.submissionbajp3.ui.movie.MovieViewModel
import com.kylix.submissionbajp3.ui.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val dataRepository: DataRepository):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(dataRepository) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(dataRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.provideDataRepository(application)?.let { ViewModelFactory(it) }
                    }
                }
            }
            return INSTANCE
        }

    }
}
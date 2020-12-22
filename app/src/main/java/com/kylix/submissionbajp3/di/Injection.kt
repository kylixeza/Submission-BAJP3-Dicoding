package com.kylix.submissionbajp3.di

import android.app.Application
import com.kylix.submissionbajp3.model.local.db.CatalogueDatabase
import com.kylix.submissionbajp3.model.remote.Helper
import com.kylix.submissionbajp3.model.remote.RemoteRepository
import com.kylix.submissionbajp3.model.repository.DataRepository
import com.kylix.submissionbajp3.model.repository.LocalRepository
import com.kylix.submissionbajp3.utils.AppExecutors

object Injection {
    fun provideDataRepository(application: Application) : DataRepository?{
        val localRepository = LocalRepository(
            CatalogueDatabase.getInstance(application).catalogueDao()
        )
        val remoteRepository = RemoteRepository.getInstance(
            Helper(
                application
            )
        )
        val executors = AppExecutors()
        return remoteRepository?.let { DataRepository.getInstance(localRepository, it, executors) }
    }
}
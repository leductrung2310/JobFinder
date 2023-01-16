package com.example.jobfinder.ui.home.di

import com.example.jobfinder.data.repository.JobRepository
import com.example.jobfinder.data.repository.JobRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {
    @Provides
    fun provideJobRepository(): JobRepository {
        return JobRepositoryImpl()
    }

}
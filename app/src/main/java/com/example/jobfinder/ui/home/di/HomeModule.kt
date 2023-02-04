package com.example.jobfinder.ui.home.di

import com.example.jobfinder.data.repository.FakeJobAPI
import com.example.jobfinder.data.repository.JobRepository
import com.example.jobfinder.data.repository.JobRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setPrettyPrinting()
            .create()
        return Retrofit.Builder()
            .baseUrl("http://192.168.8.42:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideFakeJobApi(retrofit: Retrofit): FakeJobAPI {
        return retrofit.create(FakeJobAPI::class.java)
    }

    @Provides
    fun provideJobRepository(
        firebaseFirestore: FirebaseFirestore,
        fakeJobAPI: FakeJobAPI,
        ioDispatcher: CoroutineDispatcher
    ): JobRepository {
        return JobRepositoryImpl(firebaseFirestore, fakeJobAPI, ioDispatcher)
    }

}
package com.example.jobfinder.data.repository

import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface JobRepository {
    suspend fun fetchJob(): Flow<OutCome<List<Job>>>
}

class JobRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    JobRepository {
    override suspend fun fetchJob(): Flow<OutCome<List<Job>>> = flow {
        emit(OutCome.InProgress)
        val jobs = firebaseFirestore.collection("jobs").get().await().map {
            it.toObject(Job::class.java).copy(id = it.id)
        }
        emit(OutCome.Success(jobs))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(Dispatchers.IO)
}
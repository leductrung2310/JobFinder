package com.example.jobfinder.data.repository

import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.model.toMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface JobRepository {
    fun fetchJobs(): Flow<OutCome<List<Job>>>
    fun addJob(job: Job): Flow<OutCome<String>>
    fun reportJob(job: Job): Flow<OutCome<Void?>>
}

class JobRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher
) :
    JobRepository {
    override fun fetchJobs(): Flow<OutCome<List<Job>>> = flow {
        emit(OutCome.InProgress)
        val jobs = firebaseFirestore.collection("jobs").get().await().map {
            it.toObject(Job::class.java).copy(id = it.id)
        }
        emit(OutCome.Success(jobs))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)

    override fun addJob(job: Job): Flow<OutCome<String>> = flow {
        emit(OutCome.InProgress)
        val documentReference = firebaseFirestore.collection("jobs").add(job.toMap()).await()
        emit(OutCome.Success(documentReference.id))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)

    override fun reportJob(job: Job): Flow<OutCome<Void?>> = flow {
        emit(OutCome.InProgress)
        job.id?.let {
            firebaseFirestore.collection("jobs").document(it)
                .update(mapOf("report_count" to job.report_count))
                .await()
        }
        emit(OutCome.Success(null))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)
}
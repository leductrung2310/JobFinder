package com.example.jobfinder.data.repository

import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.model.Result
import com.example.jobfinder.data.model.toMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import javax.inject.Inject

interface JobRepository {
    suspend fun fetchJobs(): Flow<OutCome<List<Job>>>
    suspend fun addJob(job: Job): Flow<OutCome<String>>
    suspend fun reportJob(job: Job): Flow<OutCome<Void?>>
    suspend fun checkFakeJob(job: Job): Flow<OutCome<Result>>
}

interface FakeJobAPI {
    @GET("result")
    @JvmSuppressWildcards
    suspend fun checkFakeJob(): Result
}

class JobRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val fakeJobAPI: FakeJobAPI,
    private val ioDispatcher: CoroutineDispatcher
) :
    JobRepository {
    override suspend fun fetchJobs(): Flow<OutCome<List<Job>>> = flow {
        emit(OutCome.InProgress)
        val jobs = firebaseFirestore.collection("jobs").get().await().map {
            it.toObject(Job::class.java).copy(id = it.id, is_fake = it["is_fake"]?.toBoolean())
        }
        emit(OutCome.Success(jobs))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)

    override suspend fun addJob(job: Job): Flow<OutCome<String>> = flow {
        emit(OutCome.InProgress)
        val documentReference = firebaseFirestore.collection("jobs").add(job.toMap()).await()
        emit(OutCome.Success(documentReference.id))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)

    override suspend fun reportJob(job: Job): Flow<OutCome<Void?>> = flow {
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

    override suspend fun checkFakeJob(job: Job): Flow<OutCome<Result>> = flow {
        emit(OutCome.InProgress)
        val result = fakeJobAPI.checkFakeJob()
        emit(OutCome.Success(result))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(ioDispatcher)
}

fun Any.toBoolean(): Boolean {
    if (this.toString() == "true") {
        return true
    }
    return false
}
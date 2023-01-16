package com.example.jobfinder.data.repository

import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface JobRepository {
    suspend fun fetchJob(): Flow<OutCome<List<Job>>>
}

class JobRepositoryImpl @Inject constructor() : JobRepository {
    override suspend fun fetchJob(): Flow<OutCome<List<Job>>> = flow<OutCome<List<Job>>> {
        emit(OutCome.InProgress)
        delay(2000)
        emit(OutCome.Success(emptyList()))
        delay(2000)
        emit(OutCome.Error(Throwable()))
    }.catch {
        emit(OutCome.Error(it))
    }.flowOn(Dispatchers.IO)
}
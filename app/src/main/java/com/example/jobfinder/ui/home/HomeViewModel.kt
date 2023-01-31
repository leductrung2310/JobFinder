package com.example.jobfinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val jobRepository: JobRepository) : ViewModel() {
    private val _homeState = MutableLiveData<HomeState>(HomeState.Waiting)
    val homeState: LiveData<HomeState>
        get() = _homeState
    private val _addJobState = MutableLiveData<AddJobState>(AddJobState.Waiting)
    val addJobState: LiveData<AddJobState>
        get() = _addJobState

    private val _searchJobLiveData = MutableLiveData<HomeState>(HomeState.Waiting)
    val searchJobLiveData: LiveData<HomeState>
        get() = _searchJobLiveData
    private var listJob: List<Job> = mutableListOf()

    init {
        fetchJob()
    }

    fun fetchJob() {
        viewModelScope.launch {
            jobRepository.fetchJobs().collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _homeState.value = HomeState.Loading
                    }
                    is OutCome.Success -> {
                        _homeState.value = HomeState.Success(it.value)
                        listJob = it.value
                    }
                    is OutCome.Error -> {
                        _homeState.value = HomeState.Error(it.value.message)
                    }
                }
            }
        }
    }

    fun addJob(job: Job) {
        viewModelScope.launch {
            jobRepository.addJob(job).collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _addJobState.value = AddJobState.Loading
                    }
                    is OutCome.Success -> {
                        _addJobState.value = AddJobState.Success(it.value)
                    }
                    is OutCome.Error -> {
                        _addJobState.value = AddJobState.Error(it.value.message)
                    }
                }
            }
        }
    }

    fun searchJob(text: String) {
        _homeState.value = HomeState.Success(listJob.filter {
            it.title?.contains(text) ?: false
        })
    }

    fun refreshListJob() {
        _homeState.value = HomeState.Success(listJob)
    }
}
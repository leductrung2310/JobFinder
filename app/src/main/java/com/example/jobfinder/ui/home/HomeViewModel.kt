package com.example.jobfinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.model.Job
import com.example.jobfinder.data.repository.JobRepository
import com.example.jobfinder.data.repository.toBoolean
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

    private val _isEnableAddJobButton = MutableLiveData(true)
    val isEnableAddJobButton: LiveData<Boolean>
        get() = _isEnableAddJobButton

    private val _reportJob = MutableLiveData<ReportJobState>(ReportJobState.Waiting)
    val reportJob: LiveData<ReportJobState>
        get() = _reportJob

    private val _selectedJob = MutableLiveData<Job>()
    val selectedJob: LiveData<Job>
        get() = _selectedJob

    init {
        fetchJob()
    }

    fun setSelectedJob(job: Job) {
        _selectedJob.value = job
    }

    fun setAddJobState() {
        _addJobState.value = AddJobState.Waiting
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
        _isEnableAddJobButton.value = false
        viewModelScope.launch {
            jobRepository.addJob(job).collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _addJobState.value = AddJobState.Loading
                    }
                    is OutCome.Success -> {
                        _addJobState.value = AddJobState.Success(it.value)
                        _isEnableAddJobButton.value = true
                    }
                    is OutCome.Error -> {
                        _addJobState.value = AddJobState.Error(it.value.message)
                        _isEnableAddJobButton.value = true
                    }
                }
            }
        }
    }

    fun reportJob(job: Job) {
        viewModelScope.launch {
            jobRepository.reportJob(job).collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _reportJob.value = ReportJobState.Loading
                    }
                    is OutCome.Success -> {
                        _reportJob.value = ReportJobState.Success(it.value)
                    }
                    is OutCome.Error -> {
                        _reportJob.value = ReportJobState.Error(it.value.message)
                    }
                }
            }
        }
    }

    fun checkFakeJob(job: Job) {
        viewModelScope.launch {
            jobRepository.checkFakeJob(job).collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _addJobState.value = AddJobState.Loading
                    }
                    is OutCome.Success -> {
                        val value = job.copy(is_fake =  it.value.predict.stringToBoolean())
                        addJob(value)
                    }
                    is OutCome.Error -> {
                        _addJobState.value = AddJobState.Error(it.value.message)
                        _isEnableAddJobButton.value = true
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

fun String.stringToBoolean(): Boolean {
    if(this == "1") {
        return true
    }
    return false
}
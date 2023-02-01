package com.example.jobfinder.ui.home

import com.example.jobfinder.base.ViewState
import com.example.jobfinder.data.model.Job

sealed class HomeState: ViewState {
    object Waiting : HomeState()
    object Loading : HomeState()
    data class Success(val jobList: List<Job>) : HomeState()
    data class Error(val error: String?) : HomeState()
}

sealed class AddJobState: ViewState {
    object Waiting : AddJobState()
    object Loading : AddJobState()
    data class Success(val jobList: String) : AddJobState()
    data class Error(val error: String?) : AddJobState()
}

sealed class ReportJobState: ViewState {
    object Waiting : ReportJobState()
    object Loading : ReportJobState()
    data class Success(val value: Void?) : ReportJobState()
    data class Error(val error: String?) : ReportJobState()
}
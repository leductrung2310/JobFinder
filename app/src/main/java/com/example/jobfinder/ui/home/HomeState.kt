package com.example.jobfinder.ui.home

import com.example.jobfinder.base.ViewState
import com.example.jobfinder.data.model.Job

sealed class HomeState: ViewState {
    object Waiting : HomeState()
    object Loading : HomeState()
    data class Success(val jobList: List<Job>) : HomeState()
    data class Error(val error: String?) : HomeState()
}
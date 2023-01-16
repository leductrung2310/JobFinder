package com.example.jobfinder.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.core.OutCome
import com.example.jobfinder.data.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val jobRepository: JobRepository) : ViewModel() {
    private val _homeState = MutableLiveData<HomeState>(HomeState.Waiting)
    val homeState: LiveData<HomeState>
        get() = _homeState

    init {
        fetchJob()
    }

     fun fetchJob() {
        viewModelScope.launch {
            jobRepository.fetchJob().collect {
                when (it) {
                    is OutCome.InProgress -> {
                        _homeState.value = HomeState.Loading
                    }
                    is OutCome.Success -> {
                        _homeState.value = HomeState.Success(it.value)
                    }
                    is OutCome.Error -> {
                        _homeState.value = HomeState.Error(it.value.message)
                    }
                }
            }
        }
    }
}
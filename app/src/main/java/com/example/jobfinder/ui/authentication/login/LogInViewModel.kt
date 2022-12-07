package com.example.jobfinder.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is log in Fragment"
    }
    val text: LiveData<String> = _text
}
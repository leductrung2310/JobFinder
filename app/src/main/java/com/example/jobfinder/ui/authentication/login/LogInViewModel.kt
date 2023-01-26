package com.example.jobfinder.ui.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.ui.authentication.usecases.AuthenticationUseCases
import com.example.jobfinder.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _logInResponse = MutableStateFlow<Response<Boolean>?>(null)
    val logInResponse: StateFlow<Response<Boolean>?>
        get() = _logInResponse

    var inputEmail by mutableStateOf("")
    fun onEmailChange(newValue: String) {
        inputEmail = newValue
    }

    var inputPassword by mutableStateOf("")
    fun onPasswordChange(newEmail: String) {
        inputPassword = newEmail
    }

    fun logIn() = viewModelScope.launch {
        _logInResponse.value = Response.Loading
        _logInResponse.value = authenticationUseCases.logInUseCases(inputEmail, inputPassword)
    }

    fun resetFlow() {
        _logInResponse.value = null
    }
}
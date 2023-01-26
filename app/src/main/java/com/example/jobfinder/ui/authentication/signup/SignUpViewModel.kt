package com.example.jobfinder.ui.authentication.signup

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
class SignUpViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {
    private val _signUpResponse = MutableStateFlow<Response<Boolean>?>(null)
    val signUpFlow: StateFlow<Response<Boolean>?>
        get() = _signUpResponse

    private val _addNameResponse = MutableStateFlow<Response<Boolean>?>(null)
    val addNameResponse: StateFlow<Response<Boolean>?>
        get() = _addNameResponse

    var inputEmail by mutableStateOf("")
    fun onEmailChange(newValue: String) {
        inputEmail = newValue
    }

    var inputPassword by mutableStateOf("")
    fun onPasswordChange(newEmail: String) {
        inputPassword = newEmail
    }

    var inputRepeatPassword by mutableStateOf("")
    fun onRepeatPasswordChange(newEmail: String) {
        inputRepeatPassword = newEmail
    }

    var inputFirstName by mutableStateOf("")
    fun onFirstNameChange(firstName: String) {
        inputFirstName = firstName
    }

    var inputLastName by mutableStateOf("")
    fun onLastNameChange(lastName: String) {
        inputLastName = lastName
    }

    fun signUp() = viewModelScope.launch {
        _signUpResponse.value = Response.Loading
        if(inputPassword == inputRepeatPassword) {
            _signUpResponse.value = authenticationUseCases.signUpUseCase(inputEmail, inputPassword)
        } else {
            _signUpResponse.value = Response.Error(Exception("The password does not match!!"))
        }
    }

    fun updateUserName() = viewModelScope.launch {
        _addNameResponse.value = Response.Loading
        _addNameResponse.value = authenticationUseCases.updateProfileUseCase(
            userName = "${inputFirstName.trim()} ${inputLastName.trim()}"
        )
    }

    fun resetFlow() {
        _signUpResponse.value = null
    }
}
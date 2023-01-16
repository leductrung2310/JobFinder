package com.example.jobfinder.ui.authentication.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.ui.authentication.usecase.UseCases
import com.example.jobfinder.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _signUpResponse = MutableStateFlow<Response<Boolean>?>(null)
    val signUpFlow: StateFlow<Response<Boolean>?>
        get() = _signUpResponse

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

    fun signUp() = viewModelScope.launch {
        _signUpResponse.value = Response.Loading
        if(inputPassword == inputRepeatPassword) {
            _signUpResponse.value = useCases.signUpUseCase(inputEmail, inputPassword)
        } else {
            _signUpResponse.value = Response.Error(Exception("The password does not match!!"))
        }
    }
}
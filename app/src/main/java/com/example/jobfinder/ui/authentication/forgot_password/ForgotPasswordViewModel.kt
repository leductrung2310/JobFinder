package com.example.jobfinder.ui.authentication.forgot_password

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
class ForgotPasswordViewModel @Inject constructor(
    private val useCases: AuthenticationUseCases
) : ViewModel() {

    private val _forgotPasswordResponse = MutableStateFlow<Response<Boolean>?>(null)
    val forgotPasswordResponse: StateFlow<Response<Boolean>?>
        get() = _forgotPasswordResponse

    var inputEmail by mutableStateOf("")
    fun onEmailChange(newValue: String) {
        inputEmail = newValue
    }

    fun forgotPassword() = viewModelScope.launch {
        _forgotPasswordResponse.value = Response.Loading
        _forgotPasswordResponse.value = useCases.forgotPasswordUseCase(inputEmail)
    }
}
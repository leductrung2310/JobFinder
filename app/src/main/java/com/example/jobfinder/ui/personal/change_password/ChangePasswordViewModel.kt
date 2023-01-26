package com.example.jobfinder.ui.personal.change_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.ui.authentication.usecases.AuthenticationUseCases
import com.example.jobfinder.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
    auth: FirebaseAuth
) : ViewModel() {
    private val _changePasswordResponse = MutableStateFlow<Response<Boolean>?>(null)
    val changePasswordResponse: StateFlow<Response<Boolean>?>
        get() = _changePasswordResponse

    var inputPassword by mutableStateOf("")
    fun onPwChange(newValue: String) {
        inputPassword = newValue
    }

    var inputNewPassword by mutableStateOf("")
    fun onNewPwChange(newEmail: String) {
        inputNewPassword = newEmail
    }

    var inputRepeatNewPassword by mutableStateOf("")
    fun onRepeatNewPwChange(newEmail: String) {
        inputRepeatNewPassword = newEmail
    }

    fun changePassword() = viewModelScope.launch {
        _changePasswordResponse.value = Response.Loading
        if (inputNewPassword == inputRepeatNewPassword) {
            _changePasswordResponse.value = authenticationUseCases.changePasswordUseCase(inputPassword, inputNewPassword)
        } else {
            _changePasswordResponse.value = Response.Error(Exception("New Password don't match"))
        }
    }

    fun resetFlow() {
        _changePasswordResponse.value = null
    }
}
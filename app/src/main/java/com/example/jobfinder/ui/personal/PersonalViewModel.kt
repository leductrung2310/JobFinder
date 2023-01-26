package com.example.jobfinder.ui.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobfinder.ui.authentication.usecases.AuthenticationUseCases
import com.example.jobfinder.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _logOutResponse = MutableStateFlow<Response<Boolean>?>(null)
    val logOutResponse : StateFlow<Response<Boolean>?>
        get() = _logOutResponse

    fun logOut() = viewModelScope.launch  {
        _logOutResponse.value = authenticationUseCases.logOutUseCases()
    }

    fun getCurrentUser(): UserInfo {
        return UserInfo(
            auth.currentUser?.displayName ?: "---",
            auth.currentUser?.email ?: "---",
            //auth.currentUser?.photoUrl.toString()
        )
    }

    fun resetFlow() {
        _logOutResponse.value = null
    }
}

data class UserInfo(
    val userName: String?,
    val email: String?,
    //val avatarLink: String? = null
)
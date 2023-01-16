package com.example.jobfinder.ui.authentication.services

import com.example.jobfinder.utils.Response

interface AuthenticationServices {
    suspend fun signUpAccount(email: String, password: String): Response<Boolean>
    suspend fun logInAccount(email: String, password: String): Response<Boolean>
}
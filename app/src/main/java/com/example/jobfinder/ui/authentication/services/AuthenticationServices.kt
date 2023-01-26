package com.example.jobfinder.ui.authentication.services

import com.example.jobfinder.utils.Response

interface AuthenticationServices {
    suspend fun signUpAccount(email: String, password: String): Response<Boolean>
    suspend fun logInAccount(email: String, password: String): Response<Boolean>
    suspend fun logOutAccount(): Response<Boolean>
    suspend fun changePassword(currentPassword: String, newPassword: String): Response<Boolean>
    suspend fun updateProfile(userName: String?, avatarLink: String?): Response<Boolean>
    suspend fun forgotPassword(email: String): Response<Boolean>
}
package com.example.jobfinder.ui.authentication.usecases

import com.example.jobfinder.ui.authentication.services.AuthenticationServices

class LogOut(
    private val services: AuthenticationServices
) {
    suspend operator fun invoke() = services.logOutAccount()
}

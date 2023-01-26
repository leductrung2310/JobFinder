package com.example.jobfinder.ui.authentication.usecases

import com.example.jobfinder.ui.authentication.services.AuthenticationServices

class ForgotPassword(
    private val services: AuthenticationServices
) {
    suspend operator fun invoke(
        email: String
    ) = services.forgotPassword(email)
}

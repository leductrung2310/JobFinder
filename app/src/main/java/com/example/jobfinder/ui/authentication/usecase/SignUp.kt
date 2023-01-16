package com.example.jobfinder.ui.authentication.usecase

import com.example.jobfinder.ui.authentication.services.AuthenticationServices

class SignUp(
    private val services: AuthenticationServices
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = services.signUpAccount(email, password)
}
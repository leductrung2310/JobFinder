package com.example.jobfinder.ui.authentication.usecases

import com.example.jobfinder.ui.authentication.services.AuthenticationServices

class ChangePassword(
    private val services: AuthenticationServices
) {
    suspend operator fun invoke(
        currentPassword: String,
        newPassword: String
    ) = services.changePassword(currentPassword, newPassword)
}
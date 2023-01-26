package com.example.jobfinder.ui.authentication.usecases

import com.example.jobfinder.ui.authentication.services.AuthenticationServices

class UpdateProfile (
    private val services: AuthenticationServices
) {
    suspend operator fun invoke(
        userName: String? = null,
        avatarLink: String? = null
    ) = services.updateProfile(userName, avatarLink)
}
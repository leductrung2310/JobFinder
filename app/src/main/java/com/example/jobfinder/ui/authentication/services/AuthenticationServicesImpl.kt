package com.example.jobfinder.ui.authentication.services

import android.net.Uri
import android.util.Log
import com.example.jobfinder.utils.Response
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationServicesImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthenticationServices {

    override suspend fun signUpAccount(email: String, password: String): Response<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun logInAccount(email: String, password: String): Response<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun logOutAccount(): Response<Boolean> {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ): Response<Boolean> {
        return try {
            auth.currentUser?.let { user ->
                user.email?.let { email ->
                    val credential = EmailAuthProvider.getCredential(email, currentPassword)
                    auth.currentUser?.reauthenticate(credential)?.await()
                }
                user.updatePassword(newPassword).await()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun updateProfile(
        userName: String?,
        avatarLink: String?
    ): Response<Boolean> {

        val profileUpdates = userProfileChangeRequest {
            displayName = userName
            //photoUri = Uri.parse(avatarLink)
        }

        return try {
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun forgotPassword(email: String): Response<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
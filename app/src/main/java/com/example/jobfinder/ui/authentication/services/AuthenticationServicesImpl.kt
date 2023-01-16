package com.example.jobfinder.ui.authentication.services

import android.util.Log
import com.example.jobfinder.utils.Response
import com.google.firebase.auth.FirebaseAuth
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
}
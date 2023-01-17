package com.example.jobfinder.ui.authentication.di

import com.example.jobfinder.ui.authentication.services.AuthenticationServices
import com.example.jobfinder.ui.authentication.services.AuthenticationServicesImpl
import com.example.jobfinder.ui.authentication.usecase.LogIn
import com.example.jobfinder.ui.authentication.usecase.SignUp
import com.example.jobfinder.ui.authentication.usecase.UseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthenticationServices(
        auth: FirebaseAuth
    ): AuthenticationServices = AuthenticationServicesImpl(auth)

    @Provides
    fun provideAuthenticationUC(
        services: AuthenticationServices
    )= UseCases(
        signUpUseCase = SignUp(services),
        logInUseCases = LogIn(services)
    )

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()
}
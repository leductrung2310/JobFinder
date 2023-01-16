package com.example.jobfinder.ui.authentication.di

import com.example.jobfinder.ui.authentication.services.AuthenticationServices
import com.example.jobfinder.ui.authentication.services.AuthenticationServicesImpl
import com.example.jobfinder.ui.authentication.usecase.SignUp
import com.example.jobfinder.ui.authentication.usecase.UseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
}
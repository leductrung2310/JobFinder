package com.example.jobfinder.ui.authentication.usecases

data class AuthenticationUseCases(
    val signUpUseCase: SignUp,
    val logInUseCases: LogIn,
    val logOutUseCases: LogOut,
    val changePasswordUseCase: ChangePassword,
    val updateProfileUseCase: UpdateProfile,
    val forgotPasswordUseCase: ForgotPassword
)
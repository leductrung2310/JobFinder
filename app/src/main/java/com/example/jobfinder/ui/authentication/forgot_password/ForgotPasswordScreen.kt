package com.example.jobfinder.ui.authentication.forgot_password

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.R
import com.example.jobfinder.ui.authentication.usecases.ForgotPassword
import com.example.jobfinder.ui.authentication.widget.*
import com.example.jobfinder.utils.Response

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val response = viewModel.forgotPasswordResponse.collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    response.value?.let {
        when(it) {
            is Response.Success -> {
                Log.e("On forgot password", "Success")
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            icon = R.drawable.ic_success,
                            title = "Send Request Successfully",
                            message = "Please review your email to update new password",
                            onDismiss = {
                                openDialog = !openDialog
                            },
                        )
                    }
                }
            }
            is Response.Error -> {
                Log.e("On forgot password", it.e?.message.toString())
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            title = "Error",
                            message = it.e?.message.toString(),
                            onDismiss = {
                                openDialog = !openDialog
                            },
                        )
                    }
                }
            }
            is Response.Loading -> {
                Log.e("On forgot password", "Loading")
                ProgressBar()
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CtTextField(
            hint = "Enter your email",
            label = "Email",
            backgroundColor = Color.White,
            cursorColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Gray,
            leadingIcon = Icons.Default.Email,
            onValueChange = viewModel::onEmailChange,
            value = viewModel.inputEmail
        )
        CtButton(text = "Send Reset Password Request",
            padding = 8.dp,
            onClick = {
                viewModel.forgotPassword()
                openDialog = true
            })
    }
}

@Composable
fun ForgotPasswordTopBar(
    onNavigateBack: () -> Unit
) {
    TopBar(text = "Forgot Password", navigateBack = { onNavigateBack() })
}
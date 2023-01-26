package com.example.jobfinder.ui.personal.change_password

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.R
import com.example.jobfinder.ui.authentication.widget.*
import com.example.jobfinder.utils.Response

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    onChangePasswordSuccess: () -> Unit,
    navigateBack: () -> Unit
) {

    val changePasswordResponse = viewModel.changePasswordResponse.collectAsState()
    var openDialog by remember { mutableStateOf(true) }
    changePasswordResponse.value?.let {
        when (it) {
            is Response.Error -> {
                Log.e("On change password", it.e?.message.toString())
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            title = "Change Password Fail",
                            message = it.e?.message.toString(),
                            onDismiss = {
                                openDialog = !openDialog
                                viewModel.resetFlow()
                            },
                        )
                    }
                }
            }
            is Response.Success -> {
                Log.e("On change password", "Success")
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            icon = R.drawable.ic_success,
                            title = "Change Password Success",
                            onDismiss = {
                                openDialog = !openDialog
                                onChangePasswordSuccess()
                                viewModel.resetFlow()
                            },
                        )
                    }
                }
            }
            is Response.Loading -> {
                Log.e("On change password", "Loading")
                ProgressBar()
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                text = "Change Password",
                navigateBack = {
                    navigateBack()
                })
        }
    ) { values ->
        Column(
            modifier = modifier.padding(values)
        ) {
            Spacer(modifier = modifier.height(100.dp))
            CtTextField(
                hint = "Enter current password",
                label = "Current Password",
                backgroundColor = Color.White,
                cursorColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Gray,
                onValueChange = viewModel::onPwChange,
                value = viewModel.inputPassword,
                trailingIcon = true,
                isTextVisible = false
            )
            CtTextField(
                hint = "Enter new password",
                label = "New Password",
                backgroundColor = Color.White,
                cursorColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Gray,
                onValueChange = viewModel::onNewPwChange,
                value = viewModel.inputNewPassword,
                trailingIcon = true,
                isTextVisible = false
            )
            CtTextField(
                hint = "Repeat new password",
                label = "New Password",
                backgroundColor = Color.White,
                cursorColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Gray,
                onValueChange = viewModel::onRepeatNewPwChange,
                value = viewModel.inputRepeatNewPassword,
                trailingIcon = true,
                isTextVisible = false
            )
            Spacer(modifier = modifier.height(100.dp))
            CtButton(text = "Change Password",
                padding = 8.dp,
                onClick = {
                    openDialog = true
                    viewModel.changePassword()
                })
        }
    }
}
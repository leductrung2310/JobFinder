package com.example.jobfinder.ui.authentication.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.R
import com.example.jobfinder.ui.authentication.widget.*
import com.example.jobfinder.ui.theme.Grey20
import com.example.jobfinder.ui.theme.PrimaryColor
import com.example.jobfinder.utils.Response


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess: () -> Unit,
    onNavigateToLogIn: () -> Unit
) {
    val authResponse = viewModel.signUpFlow.collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    authResponse.value?.let {
        when (it) {
            is Response.Error -> {
                Log.e("On sign up", it.e?.message.toString())
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            title = "Error",
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
                Log.e("On sign up", "Success")
                viewModel.updateUserName()
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            icon = R.drawable.ic_success,
                            title = "Sign Up Successfully",
                            onDismiss = {
                                onSignUpSuccess()
                                openDialog = !openDialog
                                viewModel.resetFlow()
                            },
                        )
                    }
                }
            }
            is Response.Loading -> {
                Log.e("On sign up", "Loading")
                ProgressBar()
            }
        }
    }

    Scaffold() { values ->
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = modifier
                    .background(PrimaryColor)
                    .height(80.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .width(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(Grey20)
                    .padding(values)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Create an account!",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Already a member?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )

                        TextButton(onClick = { onNavigateToLogIn() }) {
                            Text(
                                text = "Sign In",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = modifier.weight(1f)
                        ) {
                            CtTextField(
                                hint = "First Name",
                                label = "First Name",
                                backgroundColor = Color.White,
                                cursorColor = MaterialTheme.colors.primary,
                                focusedIndicatorColor = Color.Gray,
                                onValueChange = viewModel::onFirstNameChange,
                                value = viewModel.inputFirstName
                            )
                        }
                        Box(
                            modifier = modifier.weight(1f)
                        ) {
                            CtTextField(
                                hint = "Last Name",
                                label = "Last Name",
                                backgroundColor = Color.White,
                                cursorColor = MaterialTheme.colors.primary,
                                focusedIndicatorColor = Color.Gray,
                                onValueChange = viewModel::onLastNameChange,
                                value = viewModel.inputLastName
                            )
                        }
                    }
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
                    CtTextField(
                        hint = "Enter your password",
                        label = "Password",
                        backgroundColor = Color.White,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = Color.Gray,
                        onValueChange = viewModel::onPasswordChange,
                        value = viewModel.inputPassword,
                        leadingIcon = Icons.Filled.VpnKey,
                        trailingIcon = true,
                        isTextVisible = false
                    )
                    CtTextField(
                        hint = "Repeat your password",
                        label = "Password",
                        backgroundColor = Color.White,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = Color.Gray,
                        leadingIcon = Icons.Filled.VpnKey,
                        onValueChange = viewModel::onRepeatPasswordChange,
                        value = viewModel.inputRepeatPassword,
                        trailingIcon = true,
                        isTextVisible = false
                    )
                    CtButton(text = "Sign Up",
                        padding = 8.dp,
                        onClick = {
                            viewModel.signUp()
                            openDialog = true
                        })

                }
            }
        }
    }
}
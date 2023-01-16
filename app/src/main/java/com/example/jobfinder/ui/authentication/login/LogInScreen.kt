package com.example.jobfinder.ui.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobfinder.R
import com.example.jobfinder.ui.authentication.widget.CtButton
import com.example.jobfinder.ui.authentication.widget.CtTextField
import com.example.jobfinder.ui.authentication.widget.ImageButton
import com.example.jobfinder.ui.theme.Grey20
import com.example.jobfinder.ui.theme.PrimaryColor
import com.example.jobfinder.ui.theme.White

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    onNavigateToSignUp: () -> Unit
) {
    Scaffold { _ ->
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(PrimaryColor),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(Grey20)
                    .padding(12.dp)
                    .wrapContentSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome Back!",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                    Row(
                        modifier = modifier
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Don’t have an account?",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )

                        TextButton(onClick = onNavigateToSignUp) {
                            Text(
                                text = "Register",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.primary
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
//                    value = uiState.email,
//                    onValueChange = viewModel::onEmailChange
                        onValueChange = {},
                        value = ""
                    )
                    CtTextField(
                        hint = "Enter your password",
                        label = "Password",
                        backgroundColor = Color.White,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = Color.Gray,
                        onValueChange = {},
                        value = "",
                        leadingIcon = Icons.Filled.VpnKey,
//                    trailingIcon = if (uiState.isPasswordShow) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
//                    value = uiState.password,
//                    onValueChange = viewModel::onPasswordChange,
//                    isTextVisible = uiState.isPasswordShow,
//                    onShowTextClick = viewModel::onShowPasswordClick
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {},
                            modifier = modifier
                                .wrapContentSize()
                                .padding(end = 12.dp),
                            content = {
                                Text(
                                    text = "Forgot your password?",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center,
                                    fontStyle = FontStyle.Italic,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        )
                    }
                    CtButton(text = "Sign In",
                        padding = 8.dp,
                        onClick = { /*TODO*/ })
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(100.dp))
                        Text(
                            text = " or continue with ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            modifier = Modifier.padding(12.dp)
                        )
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(100.dp))
                    }
                    Row(
                        modifier = modifier.padding(bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ImageButton(
                            image = R.drawable.facebook,
                            onClick = {}
                        )

                        ImageButton(
                            image = R.drawable.google,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}
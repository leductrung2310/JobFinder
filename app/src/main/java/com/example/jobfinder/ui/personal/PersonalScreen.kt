package com.example.jobfinder.ui.personal

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jobfinder.R
import com.example.jobfinder.ui.authentication.widget.CtDialog
import com.example.jobfinder.ui.authentication.widget.ProgressBar
import com.example.jobfinder.ui.theme.*
import com.example.jobfinder.utils.Response

@Composable
fun PersonalScreen(
    modifier: Modifier = Modifier,
    viewModel: PersonalViewModel = hiltViewModel(),
    onLogOutSuccess: () -> Unit,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToAboutUs: () -> Unit
) {

    val logOutResponse = viewModel.logOutResponse.collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    logOutResponse.value?.let {
        when(it) {
            is Response.Error -> {
                Log.e("On log out", it.e?.message.toString())
                if(openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            title = "Error",
                            message = it.e?.message.toString(),
                            onDismiss = {openDialog = !openDialog},
                        )
                    }
                }
            }
            is Response.Success -> {
                Log.e("On log out", "Success")
                if (openDialog) {
                    Dialog(onDismissRequest = { openDialog = !openDialog }) {
                        CtDialog(
                            icon = R.drawable.ic_success,
                            title = "Log out Success",
                            onDismiss = {
                                openDialog = !openDialog
                                onLogOutSuccess()
                            },
                        )
                    }
                }
            }
            is Response.Loading -> {
                Log.e("On log out", "Loading")
                ProgressBar()
            }
        }
    }

    Scaffold(
        modifier = modifier,
    ) { values ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(values),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(16.dp))
            viewModel.getCurrentUser().userName?.let {
                Log.e("User Name: ", it)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.padding(vertical = halfMidPadding)
                ) {
                    Card(
                        modifier = modifier.size(80.dp),
                        shape = CircleShape,
                        backgroundColor = MaterialTheme.colors.primary,
                    ) {}
                    Text(
                        text = it[0].uppercase(),
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Text(
                    text = it,
                    style = MaterialTheme.typography.h3
                )
            }
            viewModel.getCurrentUser().email?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1, color = colorResource(
                        id = R.color.Black450
                    ),
                    modifier = modifier.padding(bottom = normalPadding)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CardItem(
                    modifier = modifier,
                    leadingIconRes = R.drawable.ic_baseline_key,
                    text = "Change your password",
                    elevation = 0.dp,
                    colorIconRes = R.color.Orange300,
                    onClick = { onNavigateToChangePassword() }
                )
                Spacer(modifier = modifier.height(smallPadding))
                CardItem(
                    modifier = modifier,
                    leadingIconRes = R.drawable.ic_info,
                    text = "Update your information",
                    elevation = 0.dp,
                    colorIconRes = R.color.Green500,
                    onClick = {}
                )
                Spacer(modifier = modifier.height(smallPadding))
                CardItem(
                    modifier = modifier,
                    leadingIconRes = R.drawable.ic_post_add,
                    text = "Your posts",
                    elevation = 0.dp,
                    colorIconRes = R.color.purple_200,
                    onClick = {}
                )
                Spacer(modifier = modifier.height(smallPadding))
                CardItem(
                    modifier = modifier,
                    leadingIconRes = R.drawable.ic_baseline_question_mark_24,
                    text = "About us",
                    elevation = 0.dp,
                    colorIconRes = R.color.Blue400,
                    onClick = { onNavigateToAboutUs() }
                )
                Spacer(modifier = modifier.height(smallPadding))
                CardItem(
                    modifier = modifier,
                    leadingIconRes = R.drawable.ic_baseline_logout_24,
                    trailingIconRes = null,
                    text = "Log out",
                    elevation = 0.dp,
                    colorIconRes = R.color.Red400,
                    onClick = {
                        openDialog = true
                        viewModel.logOut()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(
    modifier: Modifier,
    text: String,
    leadingIconRes: Int,
    trailingIconRes: Int? = R.drawable.ic_arrow_forward,
    colorIconRes: Int = R.color.primary_color,
    onClick: () -> Unit,
    elevation: Dp = 5.dp
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = onClick,
        elevation = elevation
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = leadingIconRes),
                contentDescription = null,
                tint = colorResource(id = colorIconRes),
                modifier = modifier
                    .weight(1.4f)
            )
            Text(
                modifier = modifier.weight(4f),
                text = text,
                style = MaterialTheme.typography.body1
            )
            if (trailingIconRes != null ) {
                Icon(
                    painter = painterResource(id = trailingIconRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(smallIconSize)
                        .weight(1f)
                )
            } else {
                Spacer(modifier = modifier.weight(1f))
            }
        }
    }
}
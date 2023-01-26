package com.example.jobfinder.ui.authentication.widget

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TopBar(
    text: String,
    navigateBack: () -> Unit = {},
    firstActionIcon: ImageVector? = null,
    firstAction: () -> Unit = {},
    secondActionIcon: ImageVector? = null,
    secondAction: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = text, color = MaterialTheme.colors.primary)
        },
        backgroundColor = Color.White,
        navigationIcon = {
            if(navigateBack != {}) IconButton(onClick = navigateBack) {
                Icon(Icons.Default.ArrowBack, "back icon", tint = MaterialTheme.colors.primary)
            }
        },
        actions = {
            if(firstActionIcon != null && firstAction != {} ) IconButton(onClick = firstAction) {
                Icon(firstActionIcon, "first action", tint = MaterialTheme.colors.primary)
            }
            if(secondActionIcon != null && secondAction != {}) IconButton(onClick = secondAction) {
                Icon(secondActionIcon, "second action", tint = MaterialTheme.colors.primary)
            }
        }
    )
}
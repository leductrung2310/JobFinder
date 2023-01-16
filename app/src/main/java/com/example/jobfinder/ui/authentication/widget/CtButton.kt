package com.example.jobfinder.ui.authentication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.PrimaryColor
import com.example.jobfinder.ui.theme.White
import com.example.jobfinder.ui.theme.largePadding

@Composable
fun CtButton(
    text: String,
    onClick: () -> Unit,
    padding: Dp = largePadding,
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White
) {
    Button(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .height(46.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(text = text, color = textColor)
    }
}

@Composable
fun ImageButton(
    image: Int,
    onClick: () -> Unit,
    padding: Dp = 16.dp,
    width: Dp = 140.dp,
    height: Dp = 40.dp,
    backgroundColor: Color = Color.White
) {
    Button(
        modifier = Modifier
            .padding(padding)
            .width(width)
            .height(height),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
        )
    }
}
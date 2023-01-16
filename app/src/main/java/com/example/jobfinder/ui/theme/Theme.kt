package com.example.jobfinder.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.jobfinder.ui.theme.*

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    surface = Color.White,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Black900,
    onBackground = Black900,
    onSurface = Black900,
)

@Composable
fun JobFinderTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = WeCareTypography,
        shapes = Shapes,
        content = content
    )
}
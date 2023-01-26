package com.example.jobfinder.ui.authentication.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jobfinder.ui.theme.halfMidPadding
import com.example.jobfinder.ui.theme.normalPadding


@Preview
@Composable
fun Preview() {
    CtTextField(
        value = "test",
        onValueChange = {},
        isTextVisible = true
    )
}

@Composable
fun CtTextField(
    hint: String? = null,
    label: String? = null,
    backgroundColor: Color? = null,
    cursorColor: Color? = null,
    focusedIndicatorColor: Color? = null,
    padding: Dp = halfMidPadding,
    leadingIcon: ImageVector? = null,
    trailingIcon: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    isTextVisible: Boolean = true
) {
    var passwordText by remember {
        mutableStateOf(isTextVisible)
    }
    OutlinedTextField(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        value = value,
        singleLine = true,
        onValueChange = { onValueChange(it) },
        placeholder = { if (hint != null) Text(text = hint) },
        label = { if (label != null) Text(text = label) },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (passwordText) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "leadingIcon",
                    tint = MaterialTheme.colors.primary,
                )
            }
        },
        trailingIcon = {
            if (trailingIcon) {
                IconButton(onClick = {
                    passwordText = !passwordText
                }) {
                    Icon(
                        imageVector =
                        Icons.Default.Visibility,
                        contentDescription = "trailingIcon",
                        tint = MaterialTheme.colors.primary,
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor ?: DefaultTintColor,
            cursorColor = cursorColor ?: DefaultTintColor,
            focusedIndicatorColor = focusedIndicatorColor ?: DefaultTintColor
        )
    )
}

@Composable
fun CtTextField2(
    hint: String? = null,
    label: String? = null,
    backgroundColor: Color? = null,
    cursorColor: Color? = null,
    focusedIndicatorColor: Color? = null,
    padding: Dp = halfMidPadding,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    value: String,
    onValueChange: (String) -> Unit,
    isTextVisible: Boolean = true,
    onShowTextClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        value = value,
        singleLine = true,
        onValueChange = { onValueChange(it) },
        placeholder = { if (hint != null) Text(text = hint) },
        label = { if (label != null) Text(text = label) },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (trailingIcon != null) {
                IconButton(onClick = onShowTextClick) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "trailingIcon",
                        tint = MaterialTheme.colors.primary,
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor ?: DefaultTintColor,
            cursorColor = cursorColor ?: DefaultTintColor,
            focusedIndicatorColor = focusedIndicatorColor ?: DefaultTintColor
        )
    )
}
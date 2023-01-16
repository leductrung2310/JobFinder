package com.example.jobfinder.ui.authentication.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobfinder.R
import com.example.jobfinder.ui.theme.PrimaryColor

@Composable
fun CtDialog(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_error,
    title: String,
    message: String,
    onAgree: () -> Unit,
    onDismiss: () -> Unit
) {
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {

            //.......................................................................
            Image(
                painter = painterResource(id = icon),
                contentDescription = null, // decorative
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(PrimaryColor),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = onAgree,
                    modifier = modifier.weight(1f)
                ) {
                    Text(
                        "Agree",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                Button(
                    onClick = onDismiss,
                    modifier = modifier.weight(1f)
                ) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.hcl.notflixpoc.core.ui.PhonePreview

@Composable
fun SimpleScreenError(
    errorMessage: String,
    buttonMessage: String,
    onClickRetry: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .placeholder(
                        visible = false,
                        color = Color.Black,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                    ),
                text = errorMessage,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            Button(onClick = { onClickRetry.invoke() }) {
                Text(text = buttonMessage)
            }
        }
    }
}

@PhonePreview
@Composable
fun HomeScreenErrorPreview() {
    SimpleScreenError(errorMessage = "An error occurred", buttonMessage = "Retry")
}
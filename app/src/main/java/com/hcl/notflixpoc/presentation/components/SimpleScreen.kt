package com.hcl.notflixpoc.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hcl.notflixpoc.presentation.theme.NotflixPoCTheme

@Composable
fun SimpleScreen(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotflixPoCTheme {
        SimpleScreen("NotflixPoC")
    }
}
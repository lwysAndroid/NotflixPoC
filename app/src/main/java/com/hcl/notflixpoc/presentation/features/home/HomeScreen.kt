package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hcl.notflixpoc.presentation.theme.NotflixPoCTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularMovies = viewModel.popularMovies.collectAsState().value
    LazyColumn() {
        if (popularMovies.isNullOrEmpty().not()) {
            items(popularMovies!!) {
                SimpleHomeScreen(name = "Hello ${it.name} ${it.stars}!")
            }
        }
    }

}

@Composable
fun SimpleHomeScreen(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotflixPoCTheme {
        SimpleHomeScreen("NotflixPoC")
    }
}
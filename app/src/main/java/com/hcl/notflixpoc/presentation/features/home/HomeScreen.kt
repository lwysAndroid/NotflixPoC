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
fun HomeScreenRout(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularMovies = viewModel.popularMovies.collectAsState().value
    LazyColumn() {
        if (popularMovies.isNullOrEmpty().not()) {
            items(popularMovies!!) {
                HomeScreen(name = "Hello ${it.name} ${it.stars}!")
            }
        }
    }

}

@Composable
fun HomeScreen(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotflixPoCTheme {
        HomeScreen("NotflixPoC")
    }
}
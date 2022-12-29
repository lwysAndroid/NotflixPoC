package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.hcl.notflixpoc.presentation.components.SimpleScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularMovies = viewModel.popularMovies.collectAsState().value
    LazyColumn() {
        if (popularMovies.isNullOrEmpty().not()) {
            items(popularMovies!!) {
                SimpleScreen(name = "${it.name} ${it.stars}")
            }
        }
    }

}
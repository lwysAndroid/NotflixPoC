package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularMovies = viewModel.charactersSW.collectAsState().value
    popularMovies?.let { CharactersSWList(it) }

    val scrollState = rememberScrollState()

}


package com.hcl.notflixpoc.presentation.features.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.hcl.notflixpoc.presentation.components.SimpleScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    SimpleScreen(name = "FavoritesScreen")
}
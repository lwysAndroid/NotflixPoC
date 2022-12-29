package com.hcl.notflixpoc.presentation.features.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.hcl.notflixpoc.presentation.components.SimpleScreen

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    SimpleScreen(name = "DetailScreen")
}
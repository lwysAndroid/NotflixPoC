package com.hcl.notflixpoc.presentation.features.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.hcl.notflixpoc.presentation.components.SimpleScreen

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    SimpleScreen(name = "SettingsScreen")
}

package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.hcl.notflixpoc.presentation.components.SimpleScreen
import com.hcl.notflixpoc.presentation.features.home.model.CharacterSWUI

@Composable
fun CharactersSWList(charactersSW: List<CharacterSWUI>) {
    LazyColumn() {
        if (charactersSW.isNotEmpty()) {
            items(charactersSW) {
                SimpleScreen(name = "${it.name} ${it.stars}")
            }
        }
    }
}
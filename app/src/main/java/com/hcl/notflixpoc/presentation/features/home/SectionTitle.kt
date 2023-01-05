package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hcl.notflixpoc.core.ui.PhonePreview

@Composable
fun SectionTitle(title: String) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@PhonePreview
@Composable
fun SectionTitlePreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        SectionTitle("Some Title")
    }
}
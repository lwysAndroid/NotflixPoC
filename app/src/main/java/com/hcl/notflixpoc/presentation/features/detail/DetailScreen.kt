package com.hcl.notflixpoc.presentation.features.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.hcl.notflixpoc.core.ui.PhonePreview
import com.hcl.notflixpoc.R
import com.hcl.notflixpoc.presentation.features.detail.model.DetailUi
import com.hcl.notflixpoc.presentation.features.detail.model.mockDetailUi
import com.hcl.notflixpoc.presentation.theme.Gray

@Composable
fun DetailScreenContainer(
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Int? = -1
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getMovieDetails(movieId ?: -1)
    }
    val movieDetails = viewModel.movieDetails.collectAsState().value
    movieDetails?.let { DetailScreen(detailUi = it) }
}

@Composable
fun DetailScreen(detailUi: DetailUi) {
    val scrollState = rememberScrollState()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            MovieRatingSection(popularity = detailUi.popularity, voteAverage = detailUi.voteAverage)

            //region Movie Overview
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.overview),
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSurface,
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .placeholder(
                        visible = false,
                        color = Gray,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.Gray)
                    ),
                text = detailUi.overview,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
            )
            //endregion

        }
    }
}


@PhonePreview
@Composable
fun PhonePreviewPreview() {
    DetailScreen(detailUi = mockDetailUi)
}
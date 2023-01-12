package com.hcl.notflixpoc.presentation.features.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.hcl.notflixpoc.BuildConfig
import com.hcl.notflixpoc.core.ui.PhonePreview
import com.hcl.notflixpoc.R
import com.hcl.notflixpoc.core.designsystem.component.LoadingScreen
import com.hcl.notflixpoc.core.designsystem.model.ScreenState
import com.hcl.notflixpoc.presentation.features.detail.model.DetailUi
import com.hcl.notflixpoc.presentation.features.detail.model.mockDetailUi
import com.hcl.notflixpoc.core.designsystem.component.SimpleScreenError
import com.hcl.notflixpoc.presentation.theme.Gray

@Composable
fun DetailScreenContainer(
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Int? = -1
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getMovieDetails(movieId ?: -1)
    }
    val movieDetailsState = viewModel.detailScreenState.collectAsState().value
    when (movieDetailsState) {
        is ScreenState.Loading -> {
            LoadingScreen()
        }
        is ScreenState.Success -> {
            DetailScreen(detailUi = movieDetailsState.data)
        }
        is ScreenState.Error -> {
            SimpleScreenError(
                errorMessage = stringResource(id = R.string.home_error_message),
                buttonMessage = stringResource(id = R.string.home_error_message_button),
                onClickRetry = { viewModel.getMovieDetails(movieId ?: -1) }
            )
        }
    }
}

@Composable
fun DetailScreen(
    detailUi: DetailUi
) {
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            HeaderDetails(title = detailUi.title, backdropPath = detailUi.backdropPath)

            MovieRatingSection(popularity = detailUi.popularity, voteAverage = detailUi.voteAverage)

            OverView(overview = detailUi.overview)

        }
    }
}

@Composable
fun HeaderDetails(
    modifier: Modifier = Modifier,
    title: String,
    backdropPath: String,
    @DrawableRes placeholder: Int = R.drawable.fake_movie_backdrop_path
) {
    val painter = rememberImagePainter(
        data = backdropPath,
        builder = {
            crossfade(true)
            if (BuildConfig.DEBUG) {
                placeholder(placeholder)
            }
        }
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp)
                .placeholder(
                    visible = false,
                    color = Color.Gray,
                    highlight = PlaceholderHighlight.fade()
                ),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = null
        )

        Text(
            text = title,
            fontSize = 18.sp,
            maxLines = 2,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }


}

@Composable
fun OverView(overview: String) {
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
        text = overview,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface,
        fontSize = 15.sp,
        textAlign = TextAlign.Justify,
        overflow = TextOverflow.Ellipsis,
    )
}


@PhonePreview
@Composable
fun PhonePreviewPreview() {
    DetailScreen(detailUi = mockDetailUi)
}
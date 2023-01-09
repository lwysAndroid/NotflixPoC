package com.hcl.notflixpoc.presentation.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.hcl.notflixpoc.R
import com.hcl.notflixpoc.core.designsystem.component.ItemPopularMovies
import com.hcl.notflixpoc.core.designsystem.component.ItemTrendingMovies
import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.model.fakePopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.fakeTrendingMovieUI
import com.hcl.notflixpoc.core.ui.PhonePreview
import com.hcl.notflixpoc.presentation.theme.Gray

@Composable
fun HomeScreenContainer(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickMovie: (Int) -> Unit = {}
) {

    val trendingMovies = viewModel.trendingMovies.collectAsState().value
    val popularMovies = viewModel.popularMovies.collectAsState().value
    val upcomingMovies = viewModel.upcomingMovies.collectAsState().value

    HomeScreen(
        trendingMovies = trendingMovies,
        trendingTitle = stringResource(id = R.string.trending_movies),
        onClickItemTrending = onClickMovie,
        popularMovies = popularMovies,
        popularTitle = stringResource(id = R.string.popular_movies),
        onClickItemPopular = onClickMovie,
        upcomingMovies = upcomingMovies,
        upcomingTitle = stringResource(id = R.string.upcoming_movies),
        onClickItemUpcoming = onClickMovie,
    )
}

@Composable
fun HomeScreen(
    trendingMovies: List<TrendingMovieUI>,
    trendingTitle: String,
    onClickItemTrending: (Int) -> Unit = {},
    popularMovies: List<PopularMovieUI>,
    popularTitle: String,
    onClickItemPopular: (Int) -> Unit = {},
    upcomingMovies: List<TrendingMovieUI>,
    upcomingTitle: String,
    onClickItemUpcoming: (Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (trendingMovies.isNotEmpty()) {
                TrendingMovies(
                    movies = trendingMovies,
                    title = trendingTitle,
                    onClickItem = onClickItemTrending
                )
            }

            if (popularMovies.isNotEmpty()) {
                PopularMovies(
                    movies = popularMovies,
                    title = popularTitle,
                    onClickItem = onClickItemPopular
                )
            }

            if (upcomingMovies.isNotEmpty()) {
                TrendingMovies(
                    movies = upcomingMovies,
                    title = upcomingTitle,
                    onClickItem = onClickItemUpcoming
                )
            }

        }
    }
}

@Composable
fun TrendingMovies(
    movies: List<TrendingMovieUI>,
    title: String,
    onClickItem: (Int) -> Unit,
) {
    SectionTitle(title = title)
    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .placeholder(
                visible = false,
                color = Gray,
                shape = RoundedCornerShape(4.dp),
                highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
            )
    ) {
        items(items = movies) { item ->
            ItemTrendingMovies(
                movie = item,
                onClickItem = onClickItem
            )
        }

    }
}

@Composable
fun PopularMovies(
    movies: List<PopularMovieUI>,
    title: String,
    onClickItem: (Int) -> Unit,
) {
    SectionTitle(title = title)
    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .wrapContentHeight()
            .placeholder(
                visible = false,
                color = Color.Gray,
                highlight = PlaceholderHighlight.fade()
            )
    ) {
        items(items = movies) { item ->
            ItemPopularMovies(
                modifier = Modifier
                    .width(300.dp)
                    .height(245.dp),
                popularMovieUI = item,
                onClickItem = onClickItem
            )
        }
    }

}

@PhonePreview
//@DevicePreviews
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        trendingMovies = (1..10).map { fakeTrendingMovieUI },
        trendingTitle = "Trending Movies",
        popularMovies = (1..10).map { fakePopularMovieUI },
        popularTitle = "Popular Movies",
        upcomingMovies = (1..10).map { fakeTrendingMovieUI },
        upcomingTitle = "Upcoming Movies",
    )
}


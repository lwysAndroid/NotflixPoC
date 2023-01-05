package com.hcl.notflixpoc.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.hcl.notflixpoc.R
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.model.fakeTrendingMovieUI
import com.hcl.notflixpoc.core.ui.DevicePreviews
import com.hcl.notflixpoc.presentation.theme.Gray

@Composable
fun ItemTrendingMovies(
    modifier: Modifier = Modifier,
    movie: TrendingMovieUI,
    onClickItem: (Int) -> Unit,
    @DrawableRes placeholder: Int = R.drawable.fake_movie_poster_path
) {

    Column(
        modifier = modifier
            .placeholder(
                visible = false,
                color = Color.Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
            ),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {

        Card(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .clickable { onClickItem(movie.id) }
                .placeholder(
                    visible = false,
                    color = Color.Black,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                ),
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .placeholder(
                        visible = false,
                        color = Color.Gray,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                    ),
                painter = rememberImagePainter(
                    data = movie.posterPath,
                    builder = {
                        crossfade(true)
                        placeholder(placeholder)
                    }
                ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = movie.title
            )
        }

        Text(
            modifier = Modifier
                .width(145.dp)
                .placeholder(
                    visible = false,
                    color = Color.Black,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                ),
            text = movie.title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }

}


@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@DevicePreviews
@Composable
fun ItemTrendingMoviesPreview() {
    val scrollState = rememberScrollState()
    val trendingMovies = (1..2).map { fakeTrendingMovieUI }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                items(items = trendingMovies) { item ->
                    ItemTrendingMovies(
                        movie = item,
                        onClickItem = {}
                    )
                }

            }
        }
    }
}
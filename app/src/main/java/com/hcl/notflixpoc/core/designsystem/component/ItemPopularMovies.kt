package com.hcl.notflixpoc.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.hcl.notflixpoc.core.ui.DevicePreviews
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.hcl.notflixpoc.BuildConfig
import com.hcl.notflixpoc.R
import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.fakePopularMovieUI
import com.hcl.notflixpoc.core.designsystem.util.PaletteGenerator
import com.hcl.notflixpoc.presentation.theme.Golden
import com.hcl.notflixpoc.presentation.theme.Gray


@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    popularMovieUI: PopularMovieUI,
    onClickItem: (Int) -> Unit,
    @DrawableRes placeholder: Int = R.drawable.fake_movie_backdrop_path
) {

    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(Color.Transparent) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }
    var dominantSubTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    val painter = rememberImagePainter(
        data = popularMovieUI.backdropPath,
        builder = {
            crossfade(true)
            if(BuildConfig.DEBUG){
                placeholder(placeholder)
            }
        }
    )

    if (painter.state is ImagePainter.State.Success) {
        LaunchedEffect(key1 = painter) {
            val imageDrawable = painter.imageLoader.execute(painter.request).drawable
            imageDrawable?.let {
                PaletteGenerator.generateImagePalette(imageDrawable = it) { color ->
                    dominantColor = Color(color.rgb)
                    dominantTextColor = Color(color.titleTextColor)
                    dominantSubTextColor = Color(color.bodyTextColor)
                }
            }
        }
    }


    Card(
        modifier = modifier
            .clickable { onClickItem(popularMovieUI.id) }
            .placeholder(
                visible = false,
                color = Color.Black,
                highlight = PlaceholderHighlight.fade()
            ),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Box() {

            //region Movie Cover
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = false,
                        color = Color.Black,
                        highlight = PlaceholderHighlight.fade()
                    )
                    .align(Alignment.Center),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )
            //endregion

            //region Fading Edge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                dominantColor
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                //region Movie Title
                Text(
                    text = popularMovieUI.title,
                    fontSize = 18.sp,
                    maxLines = 2,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantTextColor
                )
                //endregion

                //region Movie Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RatingBar(
                        modifier = Modifier,
                        value = popularMovieUI.voteAverage,
                        numStars = 5,
                        size = 15.dp,
                        stepSize = StepSize.HALF,
                        isIndicator = true,
                        ratingBarStyle = RatingBarStyle.Normal,
                        activeColor = Golden,
                        inactiveColor = Gray,
                        onValueChange = {},
                        onRatingChanged = {}
                    )

                    popularMovieUI.releaseDate.let {
                        Divider(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .width(1.dp)
                                .height(13.dp),
                            color = dominantSubTextColor,
                        )

                        Text(
                            modifier = Modifier,
                            text = popularMovieUI.releaseDate,
                            fontSize = 14.sp,
                            maxLines = 1,
                            style = MaterialTheme.typography.h4,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                            color = dominantSubTextColor
                        )
                    }
                }
                //endregion
            }
        }
    }
}


@DevicePreviews
@Composable
fun ItemPopularMoviesPreview() {
    ItemPopularMovies(
        popularMovieUI = fakePopularMovieUI,
        onClickItem = {},
        modifier = Modifier
//            .fillMaxSize(fraction = 0.5f)
    )
}

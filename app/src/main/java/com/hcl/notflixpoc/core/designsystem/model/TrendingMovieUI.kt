package com.hcl.notflixpoc.core.designsystem.model

import com.hcl.notflixpoc.core.designsystem.util.loadImage

data class TrendingMovieUI(
    val id: Int,
    val title: String,
    val posterPath: String,
)

val fakeTrendingMovieUI =
    with(fakeMovie) {
        TrendingMovieUI(
            id = id ?: 0,
            title = title ?: "Unknown movie",
            posterPath = posterPath?.loadImage() ?: ""
        )
    }
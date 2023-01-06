package com.hcl.notflixpoc.core.designsystem.model

import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.model.fakeMovieDomainModel

data class TrendingMovieUI(
    val id: Int,
    val title: String,
    val posterPath: String,
)

val fakeTrendingMovieUI =
    with(fakeMovieDomainModel) {
        TrendingMovieUI(
            id = id,
            title = title,
            posterPath = posterPath?.loadImage()
        )
    }
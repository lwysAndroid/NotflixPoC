package com.hcl.notflixpoc.core.designsystem.model

import com.hcl.notflixpoc.core.designsystem.util.getRating
import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.model.fakeMovieDomainModel

data class PopularMovieUI(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Float,
)

val fakePopularMovieUI =
    with(fakeMovieDomainModel) {
        PopularMovieUI(
            id = id,
            title = title,
            backdropPath = backdropPath?.loadImage() ?: "",
//            releaseDate = releaseDate.getReleaseDate()?.capitalizeEachWord()!!,
            releaseDate = "23 November, 2022",
            voteAverage = voteAverage.getRating().toFloat(),
        )
    }

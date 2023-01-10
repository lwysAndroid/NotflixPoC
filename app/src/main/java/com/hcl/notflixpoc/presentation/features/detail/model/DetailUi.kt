package com.hcl.notflixpoc.presentation.features.detail.model

import com.hcl.notflixpoc.core.designsystem.util.getPopularity
import com.hcl.notflixpoc.core.designsystem.util.getRating
import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.model.fakeMovieDetailsDomainModel

data class DetailUi(
    val title: String,
    val popularity: String,
    val voteAverage: String,
    val overview: String,
    val backdropPath: String,
)

val mockDetailUi = with(fakeMovieDetailsDomainModel) {
    DetailUi(
        title = title,
        popularity = popularity.getPopularity(),
        voteAverage = voteAverage.getRating(),
        overview = overview,
        backdropPath = backdropPath?.loadImage()
    )
}

package com.hcl.notflixpoc.core.designsystem.mapper

import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.util.capitalizeEachWord
import com.hcl.notflixpoc.core.designsystem.util.getRating
import com.hcl.notflixpoc.core.designsystem.util.getReleaseDate
import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.model.MovieDomainModel
import javax.inject.Inject

class MovieDomainModelToPopularMovieUI @Inject constructor() {

    operator fun invoke(movie: MovieDomainModel): PopularMovieUI = with(movie) {
        PopularMovieUI(
            id = id,
            title = title,
            backdropPath = backdropPath.loadImage(),
            releaseDate = releaseDate.getReleaseDate()?.capitalizeEachWord()!!,
            voteAverage = voteAverage.getRating().toFloat(),
        )
    }

}
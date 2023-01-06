package com.hcl.notflixpoc.core.designsystem.mapper

import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.model.MovieDomainModel
import javax.inject.Inject

class MovieDomainModelToTrendingMovieUI @Inject constructor() {

    operator fun invoke(movie: MovieDomainModel): TrendingMovieUI = with(movie) {
        TrendingMovieUI(id = id, title = title, posterPath = posterPath.loadImage())
    }

}
package com.hcl.notflixpoc.core.data.mapper

import com.hcl.notflixpoc.core.data.model.MovieDataModel
import com.hcl.notflixpoc.core.network.model.NetworkMovie
import javax.inject.Inject

class NetworkMovieToMovieDataModelMapper @Inject constructor() {

    operator fun invoke(movie: NetworkMovie): MovieDataModel = with(movie) {
        //TODO handled the exception  of the no-null assertion
        MovieDataModel(
            backdropPath = backdropPath ?: "",
            id = id ?: 0,
            posterPath = posterPath ?: "",
            releaseDate = releaseDate ?: "",
            title = title ?: "",
            voteAverage = voteAverage ?: 0.0,
        )
    }

}
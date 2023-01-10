package com.hcl.notflixpoc.core.data.model

data class MovieDetailsDataModel(
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val overview: String,
    val backdropPath: String,
)
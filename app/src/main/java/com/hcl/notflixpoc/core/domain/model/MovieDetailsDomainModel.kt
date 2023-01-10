package com.hcl.notflixpoc.core.domain.model

data class MovieDetailsDomainModel(
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val overview: String,
)
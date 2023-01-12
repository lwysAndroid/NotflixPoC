package com.hcl.notflixpoc.core.domain.model

data class MovieDetailsDomainModel(
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val overview: String,
    val backdropPath: String,
)

val fakeMovieDetailsDomainModel = MovieDetailsDomainModel(
    title = "Glass Onion : A Knives Out Mystery",
    popularity = 6801.729,
    voteAverage = 7.1,
    overview = "World - famous detective Benoit Blanc heads to Greece to peel back the layers of a mystery surrounding a tech billionaire and his eclectic crew of friends.",
    backdropPath = "/5gPQKfFJnl8d1edbkOzKONo4mnr.jpg",
)
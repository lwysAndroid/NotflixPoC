package com.hcl.notflixpoc.core.designsystem.model

data class Movie(
    val adult: Boolean?,
    val backdropPath: String?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val category: String?,
    val isFavorite: Boolean?,
    val cacheId: Int?,
    val mediaType: String?
)

val fakeMovie = Movie(
    adult = false,
    backdropPath = "/dKqa850uvbNSCaQCV4Im1XlzEtQ.jpg",
    id = 661374,
    originalLanguage = "en",
    originalTitle = "Glass Onion : A Knives Out Mystery",
    overview = "World - famous detective Benoit Blanc heads to Greece to peel back the layers of a mystery surrounding a tech billionaire and his eclectic crew of friends.",
    popularity = 6801.729,
    posterPath = "/vDGr1YdrlfbU9wxTOdpf3zChmv9.jpg",
    releaseDate = "2022 - 11 - 23",
    title = "Glass Onion : A Knives Out Mystery",
    video = false,
    voteAverage = 7.1,
    voteCount = 1868,
    category = "popular",
    isFavorite = null,
    cacheId = 0,
    mediaType = null
)
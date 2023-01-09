package com.hcl.notflixpoc.presentation.features.detail.model

data class DetailUi(
    val popularity: String,
    val voteAverage: String,
    val overview: String,
)

val mockDetailUi = DetailUi(
    popularity = "70",
    voteAverage = "3.8",
    overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure."
)

package com.hcl.notflixpoc.core.network.retrofit

import com.hcl.notflixpoc.core.network.model.NetworkMovieResult

interface MoviesNetworkDataSource {
    suspend fun getTrendingMovies(
        page: Int = 1,
        apiKey: String = "423f0418a6d6586755fe3d7227327ef2",
        language: String = "en",
    ): NetworkMovieResult
}
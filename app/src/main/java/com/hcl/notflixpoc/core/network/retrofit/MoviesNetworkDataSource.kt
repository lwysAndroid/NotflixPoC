package com.hcl.notflixpoc.core.network.retrofit

import com.hcl.notflixpoc.core.network.model.NetworkMovieDetails
import com.hcl.notflixpoc.core.network.model.NetworkMovieResult

interface MoviesNetworkDataSource {

    suspend fun getTrendingMovies(
        page: Int = 1,
        language: String = "en",
    ): NetworkMovieResult

    suspend fun getUpcomingMovies(
        page: Int = 1,
        language: String = "en",
    ): NetworkMovieResult

    suspend fun getPopularMovies(
        page: Int = 1,
        language: String = "en",
    ): NetworkMovieResult

    suspend fun getMovieDetails(
        movieId: Int,
        language: String = "en",
    ): NetworkMovieDetails

}
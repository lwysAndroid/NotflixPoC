package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.MovieDetailsDataModel
import com.hcl.notflixpoc.core.network.retrofit.MoviesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val network: MoviesNetworkDataSource,
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsDataModel> = flow {
        val retrieveMovieDetails = network.getMovieDetails(movieId = movieId)
        with(retrieveMovieDetails) {
            MovieDetailsDataModel(
                title = title ?: "",
                popularity = popularity ?: 0.0,
                voteAverage = voteAverage ?: 0.0,
                overview = overview ?: ""
            ).also { emit(it) }
        }
    }

}
package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.mapper.NetworkMovieToMovieDataModelMapper
import com.hcl.notflixpoc.core.data.model.MovieDataModel
import com.hcl.notflixpoc.core.network.retrofit.MoviesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val network: MoviesNetworkDataSource,
    private val mapper: NetworkMovieToMovieDataModelMapper,
) : MoviesRepository {

    override fun getTrendingMovies(): Flow<List<MovieDataModel>> {
        return flow {
            val retrieveMovies = network.getTrendingMovies()
            retrieveMovies.movies.map { mapper(it) }.also { emit(it) }
        }
    }

}
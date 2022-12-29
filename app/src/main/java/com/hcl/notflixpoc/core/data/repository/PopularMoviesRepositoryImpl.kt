package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.MovieDataModel
import com.hcl.notflixpoc.core.network.NotflixPoCNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val network: NotflixPoCNetworkDataSource
) : PopularMoviesRepository {

    override fun getPopularMovies(): Flow<List<MovieDataModel>> {
        return flow {
            network.getPeopleSW().results.map {
                MovieDataModel(name = it.name, stars = 4.7f)
            }.also { emit(it) }
        }
    }
}
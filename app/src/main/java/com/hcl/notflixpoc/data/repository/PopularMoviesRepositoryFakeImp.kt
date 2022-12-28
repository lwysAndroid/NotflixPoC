package com.hcl.notflixpoc.data.repository

import com.hcl.notflixpoc.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMoviesRepositoryFakeImp @Inject constructor() : PopularMoviesRepository {

    override fun getPopularMovies(): Flow<List<MovieDataModel>> {
        val fakeList = listOf(
            MovieDataModel(name = "One Film", stars = 4.2f),
            MovieDataModel(name = "Two Film", stars = 4.7f),
        )
        return flow {
            emit(fakeList)
        }
    }

}
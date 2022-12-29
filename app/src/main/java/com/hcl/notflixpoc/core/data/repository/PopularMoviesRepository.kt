package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<List<MovieDataModel>>
}
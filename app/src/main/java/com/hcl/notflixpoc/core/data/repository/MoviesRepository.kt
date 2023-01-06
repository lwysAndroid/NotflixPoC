package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getTrendingMovies(): Flow<List<MovieDataModel>>
}
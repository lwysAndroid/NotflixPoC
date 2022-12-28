package com.hcl.notflixpoc.data.repository

import com.hcl.notflixpoc.data.model.MovieDataModel
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<List<MovieDataModel>>
}
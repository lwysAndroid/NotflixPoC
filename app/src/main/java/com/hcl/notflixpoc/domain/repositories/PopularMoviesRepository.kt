package com.hcl.notflixpoc.domain.repositories

import com.hcl.notflixpoc.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getPopularMovies(): Flow<List<Movie>>
}
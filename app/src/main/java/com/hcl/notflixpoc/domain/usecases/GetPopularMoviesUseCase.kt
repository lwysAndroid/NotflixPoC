package com.hcl.notflixpoc.domain.usecases

import com.hcl.notflixpoc.domain.models.Movie
import com.hcl.notflixpoc.domain.repositories.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val popularMoviesRepository: PopularMoviesRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return popularMoviesRepository.getPopularMovies()
    }

}
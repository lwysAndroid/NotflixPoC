package com.hcl.notflixpoc.domain.usecases

import com.hcl.notflixpoc.data.repository.PopularMoviesRepository
import com.hcl.notflixpoc.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) {

    operator fun invoke(): Flow<List<MovieDomainModel>> {
        return popularMoviesRepository.getPopularMovies().map { list ->
            list.map { movieDataModel ->
                MovieDomainModel(
                    name = movieDataModel.name,
                    stars = movieDataModel.stars
                )
            }
        }
    }

}
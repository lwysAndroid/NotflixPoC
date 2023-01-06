package com.hcl.notflixpoc.core.domain.usecase

import com.hcl.notflixpoc.core.data.repository.MoviesRepository
import com.hcl.notflixpoc.core.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<List<MovieDomainModel>> {
        return moviesRepository.getTrendingMovies().map { list ->
            list.map { movieDataModel ->
                with(movieDataModel) {
                    MovieDomainModel(
                        backdropPath = backdropPath,
                        id = id,
                        posterPath = posterPath,
                        releaseDate = releaseDate,
                        title = title,
                        voteAverage = voteAverage,
                    )
                }
            }
        }
    }

}
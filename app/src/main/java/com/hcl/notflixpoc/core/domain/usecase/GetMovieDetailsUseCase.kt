package com.hcl.notflixpoc.core.domain.usecase

import com.hcl.notflixpoc.core.data.repository.MovieDetailsRepository
import com.hcl.notflixpoc.core.domain.model.MovieDetailsDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    operator fun invoke(movieId: Int): Flow<MovieDetailsDomainModel> {
        return movieDetailsRepository.getMovieDetails(movieId = movieId).map {
            with(it) {
                MovieDetailsDomainModel(
                    title = title,
                    popularity = popularity,
                    voteAverage = voteAverage,
                    overview = overview,
                    backdropPath = backdropPath
                )
            }
        }
    }
}
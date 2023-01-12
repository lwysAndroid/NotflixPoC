package com.hcl.notflixpoc.core.data.repository

import com.hcl.notflixpoc.core.data.model.MovieDetailsDataModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<MovieDetailsDataModel>
}
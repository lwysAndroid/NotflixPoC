package com.hcl.notflixpoc.presentation.features.home.model

import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI

data class HomeScreenDataModel(
    val trendingMovies: List<TrendingMovieUI>,
    val popularMovies: List<PopularMovieUI>,
    val upcomingMovies: List<TrendingMovieUI>,
)
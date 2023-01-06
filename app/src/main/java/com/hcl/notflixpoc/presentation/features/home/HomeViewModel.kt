package com.hcl.notflixpoc.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.designsystem.mapper.MovieDomainModelToPopularMovieUI
import com.hcl.notflixpoc.core.designsystem.mapper.MovieDomainModelToTrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.domain.usecase.GetPopularMoviesUseCase
import com.hcl.notflixpoc.core.domain.usecase.GetTrendingMoviesUseCase
import com.hcl.notflixpoc.core.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val trendingMovieUIMapper: MovieDomainModelToTrendingMovieUI,
    private val popularMovieUIMapper: MovieDomainModelToPopularMovieUI,
) : ViewModel() {

    private val _trendingMovies = MutableStateFlow<List<TrendingMovieUI>>(emptyList())
    val trendingMovies get() = _trendingMovies.asStateFlow()
    private val _popularMovies = MutableStateFlow<List<PopularMovieUI>>(emptyList())
    val popularMovies get() = _popularMovies.asStateFlow()
    private val _upcomingMovies = MutableStateFlow<List<TrendingMovieUI>>(emptyList())
    val upcomingMovies get() = _upcomingMovies.asStateFlow()

    init {
        getMoviesData()
    }

    private fun getMoviesData() {
        viewModelScope.launch {
            getTrendingMoviesUseCase().collectLatest {
                _trendingMovies.value = it.map { movieDomainModel ->
                    trendingMovieUIMapper(movieDomainModel)
                }
            }

            getUpcomingMoviesUseCase().collectLatest {
                _upcomingMovies.value = it.map { movieDomainModel ->
                    trendingMovieUIMapper(movieDomainModel)
                }
            }

            getPopularMoviesUseCase().collectLatest {
                _popularMovies.value = it.map { movieDomainModel ->
                    popularMovieUIMapper(movieDomainModel)
                }
            }
        }
    }

}
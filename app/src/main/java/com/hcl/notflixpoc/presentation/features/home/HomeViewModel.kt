package com.hcl.notflixpoc.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.model.fakePopularMovieUI
import com.hcl.notflixpoc.core.designsystem.util.loadImage
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
        //        _trendingMovies.value = (1..10).map { fakeTrendingMovieUI }
        _popularMovies.value = (1..10).map { fakePopularMovieUI }
        /*_upcomingMovies.value =
            (1..10).map {
                fakeTrendingMovieUI
                    .copy(posterPath = "/fJRt3mmZEvf8gQzoNLzjPtWpc9o.jpg".loadImage())
            }*/

        viewModelScope.launch {
            getTrendingMoviesUseCase().collectLatest {
                _trendingMovies.value = it.map { movieDomainModel ->
                    with(movieDomainModel) {
                        TrendingMovieUI(id = id, title = title, posterPath = posterPath.loadImage())
                    }
                }
            }

            getUpcomingMoviesUseCase().collectLatest {
                _upcomingMovies.value = it.map { movieDomainModel ->
                    with(movieDomainModel) {
                        TrendingMovieUI(id = id, title = title, posterPath = posterPath.loadImage())
                    }
                }
            }
        }

    }

}
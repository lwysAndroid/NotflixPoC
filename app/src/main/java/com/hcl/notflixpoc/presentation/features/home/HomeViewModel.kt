package com.hcl.notflixpoc.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.designsystem.mapper.MovieDomainModelToPopularMovieUI
import com.hcl.notflixpoc.core.designsystem.mapper.MovieDomainModelToTrendingMovieUI
import com.hcl.notflixpoc.core.designsystem.model.PopularMovieUI
import com.hcl.notflixpoc.core.designsystem.model.ScreenState
import com.hcl.notflixpoc.core.designsystem.model.TrendingMovieUI
import com.hcl.notflixpoc.core.domain.usecase.GetPopularMoviesUseCase
import com.hcl.notflixpoc.core.domain.usecase.GetTrendingMoviesUseCase
import com.hcl.notflixpoc.core.domain.usecase.GetUpcomingMoviesUseCase
import com.hcl.notflixpoc.presentation.features.home.model.HomeScreenDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    private var _trendingMovies: List<TrendingMovieUI> = emptyList()
    private var _popularMovies: List<PopularMovieUI> = emptyList()
    private var _upcomingMovies: List<TrendingMovieUI> = emptyList()

    private val _homeScreenState =
        MutableStateFlow<ScreenState<HomeScreenDataModel>>(ScreenState.Loading)
    val homeScreenState get() = _homeScreenState.asStateFlow()

    private var homeScreenDataModel: HomeScreenDataModel? = null

    init {
        getMoviesData()
    }

    fun getMoviesData() {
        _homeScreenState.value = ScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val trendingMoviesDeferred = async { getTrendingMoviesUseCase() }
                val upcomingMoviesDeferred = async { getUpcomingMoviesUseCase() }
                val popularMoviesDeferred = async { getPopularMoviesUseCase() }

                val trendingMoviesFlow = trendingMoviesDeferred.await()
                val upcomingMoviesFlow = upcomingMoviesDeferred.await()
                val popularMoviesFlow = popularMoviesDeferred.await()

                trendingMoviesFlow.collectLatest {
                    _trendingMovies = it.map { movieDomainModel ->
                        trendingMovieUIMapper(movieDomainModel)
                    }
                }

                popularMoviesFlow.collectLatest {
                    _popularMovies = it.map { movieDomainModel ->
                        popularMovieUIMapper(movieDomainModel)
                    }
                }

                upcomingMoviesFlow.collectLatest {
                    _upcomingMovies = it.map { movieDomainModel ->
                        trendingMovieUIMapper(movieDomainModel)
                    }
                }

                homeScreenDataModel = HomeScreenDataModel(
                    trendingMovies = _trendingMovies,
                    popularMovies = _popularMovies,
                    upcomingMovies = _upcomingMovies,
                )
                _homeScreenState.value = ScreenState.Success(data = homeScreenDataModel!!)

            } catch (exc: Exception) {
                _homeScreenState.value = ScreenState.Error("")
            }

        }
    }

}
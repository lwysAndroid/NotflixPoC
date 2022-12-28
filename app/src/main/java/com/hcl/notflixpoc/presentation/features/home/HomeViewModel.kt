package com.hcl.notflixpoc.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.domain.usecases.GetPopularMoviesUseCase
import com.hcl.notflixpoc.presentation.features.home.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<MovieUI>?>(emptyList())
    val popularMovies get() = _popularMovies.asStateFlow()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().collectLatest {
                _popularMovies.value = it.map { movieDomainModel ->
                    MovieUI(
                        name = movieDomainModel.name,
                        stars = movieDomainModel.stars
                    )
                }
            }
        }
    }

}
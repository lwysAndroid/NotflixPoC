package com.hcl.notflixpoc.presentation.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.designsystem.util.getPopularity
import com.hcl.notflixpoc.core.designsystem.util.getRating
import com.hcl.notflixpoc.core.domain.usecase.GetMovieDetailsUseCase
import com.hcl.notflixpoc.presentation.features.detail.model.DetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<DetailUi?>(null)
    val movieDetails get() = _movieDetails.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value = null
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId = movieId).collectLatest {
                _movieDetails.value = with(it) {
                    DetailUi(
                        title = title,
                        popularity = voteAverage.getPopularity(),
                        voteAverage = voteAverage.getRating(),
                        overview = overview
                    )
                }
            }
        }
    }
}
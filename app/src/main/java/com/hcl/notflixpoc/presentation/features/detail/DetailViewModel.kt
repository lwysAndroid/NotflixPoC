package com.hcl.notflixpoc.presentation.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.designsystem.model.ScreenState
import com.hcl.notflixpoc.core.designsystem.util.getPopularity
import com.hcl.notflixpoc.core.designsystem.util.getRating
import com.hcl.notflixpoc.core.designsystem.util.loadImage
import com.hcl.notflixpoc.core.domain.usecase.GetMovieDetailsUseCase
import com.hcl.notflixpoc.presentation.features.detail.model.DetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private lateinit var movieDetails: DetailUi

    private val _detailScreenState = MutableStateFlow<ScreenState<DetailUi>>(ScreenState.Loading)
    val detailScreenState get() = _detailScreenState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        _detailScreenState.value = ScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movieDetailsDeferred = async { getMovieDetailsUseCase(movieId = movieId) }
                val movieDetailsFlow = movieDetailsDeferred.await()
                movieDetailsFlow.collectLatest {
                    movieDetails = with(it) {
                        DetailUi(
                            title = title,
                            popularity = voteAverage.getPopularity(),
                            voteAverage = voteAverage.getRating(),
                            overview = overview,
                            backdropPath = backdropPath?.loadImage()
                        )
                    }

                    _detailScreenState.value = ScreenState.Success(data = movieDetails)
                }
            } catch (exc: Exception) {
                _detailScreenState.value = ScreenState.Error("")
            }

        }
    }
}
package com.hcl.notflixpoc.presentation.features.detail

import androidx.lifecycle.ViewModel
import com.hcl.notflixpoc.presentation.features.detail.model.DetailUi
import com.hcl.notflixpoc.presentation.features.detail.model.mockDetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {

    private val _movieDetails = MutableStateFlow<DetailUi?>(null)
    val movieDetails get() = _movieDetails.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value = null
        _movieDetails.value = mockDetailUi.copy(popularity = "85")
    }
}
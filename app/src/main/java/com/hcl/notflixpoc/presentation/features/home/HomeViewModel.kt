package com.hcl.notflixpoc.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcl.notflixpoc.core.domain.usecases.GetCharactersSWUseCase
import com.hcl.notflixpoc.presentation.features.home.model.CharacterSWUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersSWUseCase: GetCharactersSWUseCase
) : ViewModel() {

    private val _charactersSW = MutableStateFlow<List<CharacterSWUI>?>(emptyList())
    val charactersSW get() = _charactersSW.asStateFlow()

    init {
        getCharactersSW()
    }

    private fun getCharactersSW() {
        viewModelScope.launch {
            getCharactersSWUseCase().collectLatest {
                _charactersSW.value = it.map { movieDomainModel ->
                    CharacterSWUI(
                        name = movieDomainModel.name,
                        stars = movieDomainModel.stars
                    )
                }
            }
        }
    }

}
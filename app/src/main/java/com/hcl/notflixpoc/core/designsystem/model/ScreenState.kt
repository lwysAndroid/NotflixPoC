package com.hcl.notflixpoc.core.designsystem.model

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error(val error: String) : ScreenState<Nothing>()
}
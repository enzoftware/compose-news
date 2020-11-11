package com.enzoftware.composenews.utils

sealed class ViewState <out T: Any> {
    data class Success<out T: Any>(val result: T): ViewState<T>()
    object Error: ViewState<Nothing>()
    object Loading: ViewState<Nothing>()
}
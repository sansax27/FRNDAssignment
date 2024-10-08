package com.example.frndassignment.data

sealed class UIStatus<out T> {

    data class Success<T : Any>(val data: T) : UIStatus<T>()
    data class Error<T : Any>(val message: String) : UIStatus<T>()
    data object Loading: UIStatus<Nothing>()
    data object Empty: UIStatus<Nothing>()
}

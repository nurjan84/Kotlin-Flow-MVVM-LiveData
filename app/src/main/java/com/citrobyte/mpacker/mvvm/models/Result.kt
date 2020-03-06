package com.citrobyte.mpacker.mvvm.models

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
}



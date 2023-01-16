package com.example.jobfinder.core

sealed class OutCome<out T> {
    object InProgress : OutCome<Nothing>()
    data class Success<T>(val value: T) : OutCome<T>()
    data class Error(val value: Throwable) : OutCome<Nothing>()
}
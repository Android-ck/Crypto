package com.zerir.crypto.common

data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val errorMessage: String = "",
)

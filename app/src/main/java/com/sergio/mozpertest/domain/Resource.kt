package com.sergio.mozpertest.domain

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Fail<T>(data: T? = null, message: String) : Resource<T>(data, message)
}

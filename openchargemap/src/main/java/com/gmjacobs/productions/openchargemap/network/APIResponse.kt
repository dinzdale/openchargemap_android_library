package com.gmjacobs.productions.openchargemap.network

import retrofit2.HttpException

sealed class APIResponse() {
    data class Success<T>(val data: T) : APIResponse()
    data class Exception(val code: Int, val message: String) : APIResponse() {
        constructor(httpException: HttpException) : this(
            httpException.code(),
            httpException.message()
        )

        constructor(exception: java.lang.Exception) : this(
            -1,
            exception?.message ?: "Unknown API call exception"
        )
    }
}
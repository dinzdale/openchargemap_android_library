package com.gmjacobs.productions.openchargemap.network

import retrofit2.HttpException
import java.lang.Exception

sealed class APIResponse() {
    data class Success<T>(val data:T) : APIResponse()
    data class HttpError (val httpException: HttpException) : APIResponse()
    data class Exception(val exception:java.lang.Exception) : APIResponse()
}
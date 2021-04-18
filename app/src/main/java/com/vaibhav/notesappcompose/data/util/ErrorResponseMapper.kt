package com.vaibhav.notesappcompose.data.util

import com.google.gson.Gson
import com.vaibhav.notesappcompose.data.models.remote.error.ErrorResponse
import okhttp3.ResponseBody

fun mapToErrorResponse(errorBody: ResponseBody?): ErrorResponse {
    val gson = Gson()
    val error: ErrorResponse = gson.fromJson(
        errorBody?.charStream(),
        ErrorResponse::class.java
    )
    return error
}
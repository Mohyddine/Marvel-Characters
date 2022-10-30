package com.mehyo.marvelcharacters.data

/**
 * ResponseData is the default response
 * after every network call.
 * The only deference is results
 * so it is generic.
 */

data class ResponseData<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)
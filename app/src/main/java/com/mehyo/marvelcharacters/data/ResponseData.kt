package com.mehyo.marvelcharacters.data

data class ResponseData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
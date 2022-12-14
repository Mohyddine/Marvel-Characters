package com.mehyo.marvelcharacters.data

data class Character(
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)
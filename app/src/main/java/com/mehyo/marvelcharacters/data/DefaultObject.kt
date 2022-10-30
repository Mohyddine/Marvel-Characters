package com.mehyo.marvelcharacters.data

/**
 * DefaultObject is used to map
 * comic, event, story and series objects
 * since they all have the same object attributes.
 */

data class DefaultObject(
    val id: Int,
    val title: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)
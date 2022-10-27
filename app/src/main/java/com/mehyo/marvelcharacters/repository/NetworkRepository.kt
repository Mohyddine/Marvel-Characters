package com.mehyo.marvelcharacters.repository

import com.mehyo.marvelcharacters.network.MarvelAPI

class NetworkRepository(private val api: MarvelAPI) {

    /**
     * suspending function to
     * get characters from API.
     * @return all characters.
     */
    suspend fun getCharacters(
        page_size: Int,
        page: Int
    ) = api.getCharacters(page_size, page)

    /**
     * suspending function to
     * get a character by id from API.
     * @return the requested character details.
     */

    suspend fun getCharacterById(
        characterId: Int
    ) = api.getPostById(characterId)

}
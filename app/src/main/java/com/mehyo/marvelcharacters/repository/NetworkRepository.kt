package com.mehyo.marvelcharacters.repository

import com.mehyo.marvelcharacters.network.MarvelAPI

class NetworkRepository(private val api: MarvelAPI) {

    /**
     * suspending function to
     * get characters from API.
     * @return all characters.
     */
    suspend fun getCharacters(
        page: Int
    ) = api.getCharacters(page = page)

    /**
     * suspending function to
     * get a character by id from API.
     * @return the requested character details.
     */

    suspend fun getCharacterById(
        characterId: Int
    ) = api.getCharacterById(characterId)

    /**
     * suspending function to
     * get character Comics by id from API.
     * @return the requested Comics.
     */

    suspend fun getCharacterComicsById(
        characterId: Int
    ) = api.getCharacterComicsById(characterId)

    /**
     * suspending function to
     * get character Events by id from API.
     * @return the requested Events.
     */

    suspend fun getCharacterEventsById(
        characterId: Int
    ) = api.getCharacterEventsById(characterId)

    /**
     * suspending function to
     * get character Stories by id from API.
     * @return the requested Stories.
     */

    suspend fun getCharacterStoriesById(
        characterId: Int
    ) = api.getCharacterStoriesById(characterId)

    /**
     * suspending function to
     * get character Series by id from API.
     * @return the requested Series.
     */

    suspend fun getCharacterSeriesById(
        characterId: Int
    ) = api.getCharacterSeriesById(characterId)

}
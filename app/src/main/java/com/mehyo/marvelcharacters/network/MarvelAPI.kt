package com.mehyo.marvelcharacters.network

import com.mehyo.marvelcharacters.data.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface MarvelAPI {
    val apikey: String
        get() = "d86ae6a1cfabd6af57ca0206b512a57a"
    val hash: String
        get() = "9e3eb4c296003861be7cb4b524fc43fb"

    /**
     * suspending function for GET "characters" network call.
     * @return all characters.
     */

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") page_size: Int,
        @Query("offset") page: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}" network call to
     * get a character by id.
     * @return the requested character details.
     */

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}/comics" network call to
     * get a character by id.
     * @return the requested comics.
     */

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComicsById(
        @Path("characterId") characterId: Int,
        @Query("limit") page_size: Int = 3,
        @Query("orderBy") order: String = "focDate",
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}/events" network call to
     * get a character by id.
     * @return the requested events.
     */

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEventsById(
        @Path("characterId") characterId: Int,
        @Query("limit") page_size: Int = 3,
        @Query("orderBy") order: String = "startDate",
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}/stories" network call to
     * get a character by id.
     * @return the requested Stories.
     */

    @GET("characters/{characterId}/stories")
    suspend fun getCharacterStoriesById(
        @Path("characterId") characterId: Int,
        @Query("limit") page_size: Int = 3,
        @Query("orderBy") order: String = "modified",
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}/series" network call to
     * get a character by id.
     * @return the requested series.
     */

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeriesById(
        @Path("characterId") characterId: Int,
        @Query("limit") page_size: Int = 3,
        @Query("orderBy") order: String = "startYear",
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = this.apikey,
        @Query("hash") hash: String = this.hash
    ): Response<BaseResponse>

}
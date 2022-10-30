package com.mehyo.marvelcharacters.network

import com.mehyo.marvelcharacters.data.BaseResponse
import com.mehyo.marvelcharacters.data.Character
import com.mehyo.marvelcharacters.data.DefaultObject
import com.mehyo.marvelcharacters.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    /**
     * suspending function for GET "characters" network call.
     * @return all characters.
     */

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") page_size: Int = 50,
        @Query("offset") page: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<Character>>

    /**
     * suspending function for GET "characters/{characterId}" network call to
     * get a character by id.
     * @return the requested character details.
     */

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<Character>>

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
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<DefaultObject>>

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
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<DefaultObject>>

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
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<DefaultObject>>

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
        @Query("apikey") apikey: String = Constants.apikey,
        @Query("hash") hash: String = Constants.hash
    ): Response<BaseResponse<DefaultObject>>

}
package com.mehyo.marvelcharacters.network

import com.mehyo.marvelcharacters.data.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface MarvelAPI {

    /**
     * suspending function for GET "characters" network call.
     * @return all characters.
     */

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") page_size: Int,
        @Query("offset") page: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = "d86ae6a1cfabd6af57ca0206b512a57a",
        @Query("hash") hash: String = "9e3eb4c296003861be7cb4b524fc43fb"
    ): Response<BaseResponse>

    /**
     * suspending function for GET "characters/{characterId}" network call to
     * get a character by id.
     * @return the requested character details.
     */

    @GET("characters/{characterId}")
    suspend fun getPostById(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: Int = 1,
        @Query("apikey") apikey: String = "d86ae6a1cfabd6af57ca0206b512a57a",
        @Query("hash") hash: String = "9e3eb4c296003861be7cb4b524fc43fb"
    ): Response<BaseResponse>

}
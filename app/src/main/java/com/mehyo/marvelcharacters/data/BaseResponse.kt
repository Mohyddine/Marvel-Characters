package com.mehyo.marvelcharacters.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    val code: Int,
    val status: String,
    @SerializedName("data")
    @Expose
    val responseData: ResponseData
)

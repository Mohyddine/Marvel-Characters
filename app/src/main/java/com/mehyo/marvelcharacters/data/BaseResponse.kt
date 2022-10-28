package com.mehyo.marvelcharacters.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val code: Int,
    val status: String,
    @SerializedName("data")
    @Expose
    val responseData: ResponseData<T>
)

package com.example.discountclub.data.remote.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data") val data: T,
)
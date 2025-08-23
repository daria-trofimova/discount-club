package com.example.discountclub.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PurchaseResponse(
    @SerializedName("date") val date: Date,
    @SerializedName("name") val name: List<String>,
)
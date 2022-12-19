package com.kimjiun.exratecalc.retrofit.model

import com.google.gson.annotations.SerializedName

data class RateInfo (
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("timestamp")
    val timestamp: String,

    @SerializedName("source")
    val source: String,

    @SerializedName("quotes")
    val quotes: Quotes
)
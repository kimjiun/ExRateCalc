package com.kimjiun.exratecalc.retrofit.model

import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("USDKRW")
    val usdKrw: Double,

    @SerializedName("USDJPY")
    val usdJpy: Double,

    @SerializedName("USDPHP")
    val usdPhp: Double,
)

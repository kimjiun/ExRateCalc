package com.kimjiun.exratecalc.retrofit

import com.kimjiun.exratecalc.retrofit.model.RateInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {
    @GET("/currency_data/live")
    fun getNumber(
        @Header("apikey") apikey: String,
        @Query("source") base: String,
        @Query("currencies") symbols: String): Call<RateInfo>
}
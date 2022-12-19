package com.kimjiun.exratecalc.retrofit

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kimjiun.exratecalc.BuildConfig
import com.kimjiun.exratecalc.retrofit.model.RateInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log
import kotlin.text.Typography.quote

class RetrofitRepo{
    val TAG = "JIUN/${javaClass.name}"
    var rateInfoData: MutableLiveData<RateInfo> = MutableLiveData()

    fun getRateInfo(){
        RetrofitImpl.service.getNumber(BuildConfig.CURRENCY_API_KEY, "USD", "KRW,JPY,PHP")
            .enqueue(object : Callback<RateInfo> {
            override fun onResponse(call: Call<RateInfo>, response: Response<RateInfo>) {
                if(response.isSuccessful) {
                    val result = response.body()
                    Log.d(TAG, "RST : ${result}")
                    rateInfoData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RateInfo>, t: Throwable) {
                rateInfoData.postValue(null)
                t.printStackTrace()
            }
        })
    }

    fun getRateInfoData() : LiveData<RateInfo> {
        return this.rateInfoData
    }

    fun getRateByCountry(country: String): Double{
        var result = 1.5

        when(country){
            "KRW" -> result = rateInfoData.value?.quotes?.usdKrw ?: result
            "JPY" -> result = rateInfoData.value?.quotes?.usdJpy ?: result
            "PHP" -> result = rateInfoData.value?.quotes?.usdPhp ?: result
        }

        return result
    }
}
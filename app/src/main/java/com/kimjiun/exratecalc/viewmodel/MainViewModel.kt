package com.kimjiun.exratecalc.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kimjiun.exratecalc.retrofit.RetrofitRepo
import com.kimjiun.exratecalc.retrofit.model.RateInfo

class MainViewModel (application: Application): AndroidViewModel(application) {
    val TAG = "JIUN/${javaClass.name}"

    val repo: RetrofitRepo
    val rateInfoData: LiveData<RateInfo>

    private var remittance = MutableLiveData<String>()
    val remittanceData: LiveData<String>
        get() = remittance

    val countryText = MutableLiveData<String>()
    val rateText = MutableLiveData<String>()
    val timeText = MutableLiveData<String>()
    val resultText = MutableLiveData<String>()

    init {
        repo = RetrofitRepo()
        rateInfoData = repo.getRateInfoData()
        resultText.value = "0.00"
    }

    fun getRateInfo(){
        repo.getRateInfo()
    }

    fun getRateInfoLiveData() : LiveData<RateInfo> {
        return this.rateInfoData
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.d(TAG, "$s / $start / $before / $count")
        val country = "KRW"

        if(s.isNotEmpty()){
            val input = s.toString().toInt()
            resultText.value = "${repo.getRateByCountry(country) * input}"
        }
        else{
            resultText.value = "0.00"
        }
    }
}
package com.example.horoscopeapp.data.network

import com.example.horoscopeapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    @GET("/{sign}/")
    suspend fun getHororscope(@Path("sign")sing: String): PredictionResponse

}
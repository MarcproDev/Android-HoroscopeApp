package com.example.horoscopeapp.data

import android.util.Log
import com.example.horoscopeapp.data.network.HoroscopeApiService
import com.example.horoscopeapp.domain.Repository
import com.example.horoscopeapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sing: String): PredictionModel? {
        runCatching { apiService.getHororscope(sing) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Jamma Jack", "Ha ocurrido un error ${it.message}") }
        return null
    }
}

package com.example.horoscopeapp.data.network.response

import com.example.horoscopeapp.motherobject.HoroscopeMotherObject
import com.example.horoscopeapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import io.kotlintest.specs.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest {

    @Test
    fun `toDomain should return a correct PredictionModel`() {

        //Given
        val horoscopeResponse = anyResponse

        //When
        val predictionModel = horoscopeResponse.toDomain()

        //Then
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscope shouldBe  horoscopeResponse.horoscope
    }

}
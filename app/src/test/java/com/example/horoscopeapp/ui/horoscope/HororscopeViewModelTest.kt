package com.example.horoscopeapp.ui.horoscope

import com.example.horoscopeapp.data.provider.HoroscopeProvider
import com.example.horoscopeapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HororscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider//test mediante mokk
    private lateinit var viewModel: HororscopeViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun  `when viewModel is created then horoscopes are loaded`() {
        every { horoscopeProvider.getHoroscope() } returns horoscopeInfoList
        viewModel = HororscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())
    }

}
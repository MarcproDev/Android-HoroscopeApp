package com.example.horoscopeapp.ui.provider

import com.example.horoscopeapp.ui.model.LuckyModel
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class RandomCardProviderTest {

    @Test
    fun `getRandomCard should return a random card`() {

        val randomCard = RandomCardProvider()


        val card: LuckyModel?  = randomCard.getLucky()

        assertNotNull(card)

    }

}
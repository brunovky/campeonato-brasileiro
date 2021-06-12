package com.brunooliveira.campeonatobrasileiro.repository

import com.brunooliveira.campeonatobrasileiro.model.MatchDay
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FootballDataRepositoryTest {

    private lateinit var api: FootballDataApi
    private lateinit var repository: FootballDataRepository

    private val validRound = 1
    private val successMatchDay = MatchDay(listOf())

    @Before
    fun setUp() {
        api = mock()
        runBlocking {
            whenever(api.getMatchDay(validRound)).thenReturn(successMatchDay)
        }
        repository = FootballDataRepository(api)
    }

    @Test
    fun testGetMatchDay() = runBlocking {
        assertEquals(successMatchDay, repository.getMatchDay(validRound))
    }

}
package com.brunooliveira.campeonatobrasileiro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.brunooliveira.campeonatobrasileiro.model.Match
import com.brunooliveira.campeonatobrasileiro.model.MatchDay
import com.brunooliveira.campeonatobrasileiro.repository.FootballDataRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: FootballDataRepository
    private lateinit var observer: Observer<List<Match>>

    private val successMatchDay = MatchDay(listOf())
    private val validRound = 1

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mock()
        runBlocking {
            whenever(repository.getMatchDay(validRound)).thenReturn(successMatchDay)
        }
        viewModel = MainViewModel(repository)
        observer = mock()
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testLoadMatches() = runBlocking {
        viewModel.matches.observeForever(observer)
        viewModel.loadMatches(validRound - 1)
        delay(10)
        verify(repository).getMatchDay(validRound)
        verify(observer, timeout(50)).onChanged(successMatchDay.matches)
    }

}
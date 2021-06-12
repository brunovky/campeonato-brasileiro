package com.brunooliveira.campeonatobrasileiro.repository

class FootballDataRepository(private val api: FootballDataApi) {

    suspend fun getMatchDay(round: Int) = api.getMatchDay(round)

}
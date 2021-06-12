package com.brunooliveira.campeonatobrasileiro.repository

import com.brunooliveira.campeonatobrasileiro.model.MatchDay
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballDataApi {

    @GET("competitions/2013/matches")
    suspend fun getMatchDay(@Query("matchday") round: Int): MatchDay

}

fun providerApi(retrofit: Retrofit): FootballDataApi = retrofit.create(FootballDataApi::class.java)
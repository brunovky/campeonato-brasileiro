package com.brunooliveira.campeonatobrasileiro.model

data class Match(val date: String,
                 val status: String,
                 val score: Score,
                 val homeTeam: Team,
                 val awayTeam: Team)

data class Score(val fullTime: FullTime)

data class FullTime(val homeTeam: Int, val awayTeam: Int)

data class Team(val name: String)

package com.brunooliveira.campeonatobrasileiro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunooliveira.campeonatobrasileiro.model.Match
import com.brunooliveira.campeonatobrasileiro.repository.FootballDataRepository
import com.brunooliveira.campeonatobrasileiro.resource.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: FootballDataRepository): ViewModel() {

    val rounds = MutableLiveData<List<String>>()
    val matches = MutableLiveData<List<Match>>()
    val loading = MutableLiveData<Boolean>()

    init {
        rounds.value = (1..20).map {
            "Rodada $it"
        }
    }

    fun loadMatches(round: Int) {
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val matchDay = repository.getMatchDay(round + 1)
            EspressoIdlingResource.decrement()
            matches.postValue(matchDay.matches)
            loading.postValue(false)
        }
    }

}
package com.brunooliveira.campeonatobrasileiro.resource

import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {

    companion object {

        val idlingResource = CountingIdlingResource("GLOBAL")

        fun increment() {
            idlingResource.increment()
        }

        fun decrement() {
            idlingResource.decrement()
        }

    }
}
package com.brunooliveira.campeonatobrasileiro.view

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brunooliveira.campeonatobrasileiro.R
import com.brunooliveira.campeonatobrasileiro.resource.EspressoIdlingResource
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testInitScreen() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.til_rounds)).check(AutoCompleteTextViewItemCountAssertion(20))
        onView(withId(R.id.list_matches)).check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun testChooseRound() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.til_rounds)).perform(click())
        onData(anything()).atPosition(0).inRoot(isPlatformPopup()).perform(click())
        onView(withId(R.id.list_matches)).check(RecyclerViewItemCountAssertion(10))
    }

}

class AutoCompleteTextViewItemCountAssertion(private val expectedCount: Int): ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val autoCompleteTextView: AutoCompleteTextView =
            ((view as TextInputLayout).editText as AutoCompleteTextView)
        assertThat(autoCompleteTextView.adapter.count, `is`(expectedCount))
    }

}

class RecyclerViewItemCountAssertion(private val expectedCount: Int): ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView: RecyclerView = view as RecyclerView
        assertThat(recyclerView.adapter?.itemCount, `is`(expectedCount))
    }

}
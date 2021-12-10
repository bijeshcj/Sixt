package com.bijesh.application.screens

import android.util.Log
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bijesh.application.R
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class StartFragmentTest {

    private val TAG = "StartFragmentTest"
    private lateinit var fragmentScenario: FragmentScenario<StartFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_Application)
        fragmentScenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testOpenFragment(){
        onView(withId(R.id.button)).perform(click())
    }

    @After
    fun tearDown() {

    }
}
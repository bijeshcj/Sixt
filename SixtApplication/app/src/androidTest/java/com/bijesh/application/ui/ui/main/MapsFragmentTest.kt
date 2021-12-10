package com.bijesh.application.ui.ui.main

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.bijesh.application.R
import com.bijesh.application.models.CarsItem
import com.bijesh.application.screens.StartFragment
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class MapsFragmentTest{
    private val TAG = "MapsFragmentTest"
    private lateinit var fragmentScenario: FragmentScenario<MapsFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_Application)
        fragmentScenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testOpenFragment(){
        fragmentScenario.moveToState(Lifecycle.State.CREATED)
        with(launchFragment<MapsFragment>()) {
            fragmentScenario.onFragment {
                bundleOf(
                    Pair(
                        "carList", Arrays.asList(
                            CarsItem(
                                "", "",
                                1.2, "", "", "", "", 52.5200, "", 13.4050,
                                "", "", "", "", "", ""
                            )
                        )
                    )
                )
            }
        }
    }

    @After
    fun tearDown() {

    }
}
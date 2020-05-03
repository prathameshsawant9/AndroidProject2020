package com.leo.android.project.ui.main

import android.view.Gravity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.leo.android.project.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest{

    @get: Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun a_test_navigation_drawer_initial_state(){
        onView(withId(R.id.drawerLayout))
            .check(matches(isClosed(Gravity.LEFT)))
    }

    @Test
    fun b_test_navigation_drawer_open_state(){
        onView(withId(R.id.drawerLayout))
            .perform(DrawerActions.open())
            .check(matches(isOpen()))
    }
}
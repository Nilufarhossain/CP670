package com.example.androidassignments

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testButtonsExist() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withId(R.id.start_chat_button)).check(matches(isDisplayed()))
    }

    @Test
    fun testStartListItemsActivity() {
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun testStartChatWindow() {
        onView(withId(R.id.start_chat_button)).perform(click())
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
}

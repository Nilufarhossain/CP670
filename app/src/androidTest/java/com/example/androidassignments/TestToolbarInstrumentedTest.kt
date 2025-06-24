package com.example.androidassignments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.R.id.snackbar_text
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestToolbarInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TestToolbar::class.java)


    @Test
    fun testDisplaySnackbar() {
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(snackbar_text), withText("My custom snackbar message!")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDisplaySnackbarWithDefaultMessage() {

        onView(withId(R.id.action_one)).perform(click())

        onView(allOf(
            withId(com.google.android.material.R.id.snackbar_text),
            withText("You selected item 1")
        )).check(matches(isDisplayed()))
    }

    @Test
    fun testShowCustomDialog() {

        onView(withId(R.id.action_three)).perform(click())
        onView(withText("Ok")).check(matches(isDisplayed()))
    }

    @Test
    fun testInputMessageInDialog() {

        onView(withId(R.id.action_three)).perform(click())

        onView(withId(R.id.new_message)).perform(typeText("Test Message"), closeSoftKeyboard())

        onView(withText("Ok")).perform(click())

        onView(allOf(
            withId(com.google.android.material.R.id.snackbar_text),
            withText("New message: Test Message")
        )).check(matches(isDisplayed()))
    }
}
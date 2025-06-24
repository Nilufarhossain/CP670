package com.example.androidassignments

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatWindowInstrumentedTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(activity_chat_window::class.java)

    @Test
    fun testSendMessageUpdatesList() {
        Thread.sleep(500)
        onView(withId(R.id.message_box))
            .perform(typeText("Hello from Espresso!"), closeSoftKeyboard())
        onView(withId(R.id.send_button)).perform(click())

        // Check if the message is displayed in the ListView or RecyclerView
        onView(withText("You: Hello from Espresso!")).check(matches(isDisplayed()))
    }

    @Test
    fun testSendEmptyMessageDoesNothing() {
        Thread.sleep(500)
        onView(withId(R.id.message_box))
            .perform(typeText("   "), closeSoftKeyboard())
        onView(withId(R.id.send_button)).perform(click())

        // This assumes blank messages are ignored
        onView(withText("You:")).check(doesNotExist())
    }
}

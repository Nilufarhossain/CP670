package com.example.androidassignments

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class TestToolbarTest {

    private lateinit var activity: TestToolbar

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(TestToolbar::class.java)
            .create()
            .start()
            .resume()
            .get()
    }

    @Test
    fun testGetSnackbarMessageWithMessageSet() {
        activity.newMessage = "Hello, World!"
        val result = activity.getSnackbarMessage()
        assertEquals("Hello, World!", result)
    }

    @Test
    fun testGetSnackbarMessageWhenEmpty() {
        activity.newMessage = null
        val result = activity.getSnackbarMessage()
        assertEquals("No message set", result)
    }
}

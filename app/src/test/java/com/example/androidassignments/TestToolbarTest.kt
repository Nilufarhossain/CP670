package com.example.androidassignments

import org.junit.Assert.assertEquals
import org.junit.Test

class TestToolbarTest {

    @Test
    fun testMessageSet() {
        val result = TestToolbar.getSnackbarMessage("Hello World")
        assertEquals("Hello World", result)
    }

    @Test
    fun testMessageEmpty() {
        val result = TestToolbar.getSnackbarMessage("")
        assertEquals("No message set", result)
    }

    @Test
    fun testMessageNull() {
        val result = TestToolbar.getSnackbarMessage(null)
        assertEquals("No message set", result)
    }
}

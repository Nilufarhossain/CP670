package com.example.androidassignments

import org.junit.Assert.*
import org.junit.Test

class MainActivityLogicUnitTest {

    private val mainActivity = MainActivity()

    @Test
    fun testNavigationToListItemsActivity() {
        val result = mainActivity.getNavigationTarget("navigate")
        assertEquals(ListItemsActivity::class.java, result)
    }

    @Test
    fun testNavigationToChatWindowActivity() {
        val result = mainActivity.getNavigationTarget("chat")
        assertEquals(activity_chat_window::class.java, result)
    }

    @Test
    fun testInvalidNavigationReturnsNull() {
        val result = mainActivity.getNavigationTarget("unknown")
        assertNull(result)
    }
}

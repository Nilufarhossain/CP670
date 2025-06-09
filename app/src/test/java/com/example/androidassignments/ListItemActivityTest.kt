package com.example.androidassignments

import org.junit.Assert.assertEquals
import org.junit.Test

class ListItemsActivityUnitTest {

    private val activity = ListItemsActivity()

    @Test
    fun toggleSwitch_shouldReturnOpposite() {
        assertEquals(true, activity.toggleSwitch(false))
        assertEquals(false, activity.toggleSwitch(true))
    }

    @Test
    fun isCheckboxChecked_shouldReturnCorrectMessage() {
        assertEquals("Dialog should show", activity.isCheckboxChecked(true))
        assertEquals("Dialog should not show", activity.isCheckboxChecked(false))
    }

    @Test
    fun onImageButtonClick_shouldReturnClickMessage() {
        assertEquals("Image button clicked", activity.onImageButtonClick())
    }
}

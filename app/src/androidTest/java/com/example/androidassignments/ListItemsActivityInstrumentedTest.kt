package com.example.androidassignments

import android.Manifest
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListItemsActivityInstrumentedTest {

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.CAMERA)

    @Test
    fun test_switch_toggles() {
        ActivityScenario.launch(ListItemsActivity::class.java)
        onView(withId(R.id.my_switch)).perform(click())
        onView(withId(R.id.my_switch)).check { view, _ -> assert((view as android.widget.Switch).isChecked) }
    }

    @Test
    fun test_checkbox_shows_dialog() {
        ActivityScenario.launch(ListItemsActivity::class.java)
        onView(withId(R.id.my_checkbox)).perform(click())
    }

    @Test
    fun test_image_button_click() {
        ActivityScenario.launch(ListItemsActivity::class.java)
        onView(withId(R.id.imageButton)).perform(click())
    }
}

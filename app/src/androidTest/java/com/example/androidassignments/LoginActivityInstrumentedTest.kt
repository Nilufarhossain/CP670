package com.example.androidassignments

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidassignments.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {

    @Test
    fun test_login_with_valid_credentials() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.editTextEmail)).perform(typeText("user"), closeSoftKeyboard())
        onView(withId(R.id.password_field)).perform(typeText("pass"), closeSoftKeyboard())
        onView(withId(R.id.button2)).perform(click())

        // Expected: either go to main screen or show success (depends on your code)
        // You can verify using intended() or check a view
    }

    @Test
    fun test_login_button_exists() {
        ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.button2)).check { view, _ -> assert(view.isShown) }
    }
}


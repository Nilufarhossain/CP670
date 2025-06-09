package com.example.androidassignments

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class LoginValidator {
    fun isValid(username: String, password: String): Boolean {
        return username == "admin" && password == "admin123"
    }
}
class LoginUnitTest {
    private lateinit var validator: LoginValidator

    @Before
    fun setUp() {
        validator = LoginValidator()
    }

    @Test
    fun testValidLogin() {
        assertTrue(validator.isValid("admin", "admin123"))
    }

    @Test
    fun testInvalidLogin() {
        assertFalse(validator.isValid("user", "wrongpass"))
    }
}
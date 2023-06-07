package com.cursosant.authbase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthTest {

    @Test
    fun login_complete_returns_true() {
        val isAuthenticated = userAuthentication("ant@gmail.com", "1234")
        assertTrue("Authentication Successful", isAuthenticated)
    }

    @Test
    fun login_complete_returns_false() {
        val isAuthenticated = userAuthentication("an@gmail.com", "1234")
        assertFalse("Authentication Error", isAuthenticated)
    }

    @Test
    fun login_empty_email_returns_false() {
        val isAuthenticated = userAuthentication("", "1234")
        assertFalse("Authentication Error", isAuthenticated)
    }

    @Test
    fun login_null_email_returns_false() {
        val isAuthenticated = userAuthenticationTDD(null, "1234")
        assertFalse("Authentication Error", isAuthenticated)
    }

    @Test
    fun login_null_password_returns_false() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", null)
        assertFalse("Authentication Error", isAuthenticated)
    }
}
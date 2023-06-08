package com.cursosant.authbase

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.NullPointerException

class AuthTDDTest {

    private var email: String? = null
    private var password: String? = null

    @Before
    fun setup() {
        email = "ant@gmail.com"
        password = "1234"
    }

    @After
    fun tearDown() {
        email = null
        password = null
    }

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        assertEquals(AuthEvent.USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent() {
        email = "an@gmail.com"
        val isAuthenticated = userAuthenticationTDD(email, password)
        assertEquals(AuthEvent.USER_NOT_EXISTS, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent() {
        email = ""
        val isAuthenticated = userAuthenticationTDD(email, "1234")
        assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent() {
        password = ""
        val isAuthenticated = userAuthenticationTDD(email, password)
        assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "")
        assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant.gmail.com", "1234")
        assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123E")
        assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant.gmail.com", "123E")
        assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

   @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException() {
       val isAuthenticated = userAuthenticationTDD(null, "123E")
       assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }
    /*
        @Test
        fun login_nullPassword_returnsException() {
        }

        @Test
        fun login_nullForm_returnsException() {
        }

        @Test
        fun login_completeForm_errorLengthPassword_returnsFailEvent() {
        }*/
}
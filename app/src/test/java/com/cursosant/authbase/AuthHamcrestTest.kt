package com.cursosant.authbase

import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.both
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.everyItem
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItemInArray
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class AuthHamcrestTest {

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
    //given-when-then
    @Test
    fun loginUser_formWithCorrectData_returnsSuccessEvent() {
        val result = userAuthenticationTDD(email, password)
        assertThat(AuthEvent.USER_EXISTS, `is` (result))
    }

    @Test
    fun loginUser_formWithWrongData_returnsFailEvent() {
        email = "an@gmail.com"
        val result = userAuthenticationTDD(email, password)
        assertThat(AuthEvent.USER_NOT_EXISTS, `is` (result))
    }

    @Test
    fun loginUser_formWithEmptyEmail_returnsFailEvent() {
        email = ""
        val result = userAuthenticationTDD(email, "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is` (result))
    }

    @Test
    fun loginUser_formWithEmptyPassword_returnsFailEvent() {
        password = ""
        val result = userAuthenticationTDD(email, password)
        assertThat(AuthEvent.EMPTY_PASSWORD, `is` (result))
    }

    @Test
    fun loginUser_formWithEmptyData_returnsFailEvent() {
        val result = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is` (result))
    }

    @Test
    fun loginUser_formWithInvalidEmail_returnsFailEvent() {
        val result = userAuthenticationTDD("ant.gmail.com", "1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is` (result))
    }

    @Test
    fun loginUser_formWithInvalidPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "123E")
        assertThat(AuthEvent.INVALID_PASSWORD, `is` (result))
    }

    @Test
    fun loginUser_formWithInvalidUser_returnsFailEvent() {
        val result = userAuthenticationTDD("ant.gmail.com", "123E")
        assertThat(AuthEvent.INVALID_USER, `is` (result))
    }

    @Test(expected = AuthException::class)
    fun loginUser_formWithNullEmail_returnsException() {
        val result = userAuthenticationTDD(null, "123E")
        assertThat(AuthEvent.NULL_EMAIL, `is` (result))
    }

    @Test
    fun loginUser_formWithNullPassword_returnsException() {
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD(email, null))
        }
    }

    @Test
    fun loginUser_formWithBothNullValues_returnsException() {
        try {
            val result = userAuthenticationTDD(null, null)
            assertThat(AuthEvent.NULL_FORM, `is` (result))
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM, `is` (it.authEvent))
            }
        }
    }

    //@Ignore("sample message : still not defined")
    @Test
    fun loginUser_formWithExceededLengthPassword_returnsFailEvent() {
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD(email, "1234567689"))
        }
    }

    @Test
    fun checkNames_differentUser_match() {
        assertThat("Ruben Pla", both(containsString("Pla"))
            .and(startsWith("R"))
            .and(endsWith("a")))
    }

    @Test
    fun checkData_emailPassword_notMatch() {
        assertThat(email, not(`is`(password)))
    }

    @Test
    fun checkExists_newEmail_returnsString() {
        val oldEmail = email!!
        val newEmail = "ant@cursoandroid.com"
        val emailList = arrayOf(oldEmail, newEmail, "oldEmailgmail.com")
        assertThat(emailList, hasItemInArray(newEmail))
    }

    @Test
    fun checkDomain_arrayEmails_returnsString() {
        val oldEmail = email!!
        val newEmail = "ant@cursoandroid.com"
        val emailList = arrayListOf(oldEmail, newEmail, "oldEmail@hotmail.com")
        assertThat(emailList, hasItem(endsWith("hotmail.com")))
    }
}
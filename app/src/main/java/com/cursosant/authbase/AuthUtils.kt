package com.cursosant.authbase

/****
 * Project: Auth
 * From: com.cursosant.authbase
 * Created by Alain Nicolás Tello on 14/12/21 at 12:05
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "ant@gmail.com" && password == "1234"){
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvent {
    val maxLength = 4
    if (email == null && password == null) throw AuthException(AuthEvent.NULL_FORM)
    if (email == null) throw AuthException(AuthEvent.NULL_EMAIL)
    if (password == null) throw AuthException(AuthEvent.NULL_PASSWORD)
    if (password.length > maxLength) throw AuthException(AuthEvent.TOO_LONG_PASSWORD)

    val isInvalidEmail = email.isNotEmpty() && !isEmailValid(email)
    val isInvalidPassword = password.isNotEmpty() && !isValidPassword(password)

    if (isInvalidEmail && isInvalidPassword) return AuthEvent.INVALID_USER
    if (isInvalidEmail) return AuthEvent.INVALID_EMAIL
    if (isInvalidPassword) return AuthEvent.INVALID_PASSWORD

    if (email.isEmpty() && password.isEmpty()) return AuthEvent.EMPTY_FORM
    if (email.isEmpty()) return AuthEvent.EMPTY_EMAIL
    if (password.isEmpty()) return AuthEvent.EMPTY_PASSWORD

    if (email != "ant@gmail.com") return AuthEvent.USER_NOT_EXISTS
    if (email == "ant@gmail.com" && password == "1234"){
        return AuthEvent.USER_EXISTS
    }

    return AuthEvent.UNKNOWN_EVENT
}

fun isEmailValid(email: String?): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return email?.let { EMAIL_REGEX.toRegex().matches(it) } == true;
}

fun isValidPassword(password: String?): Boolean {
    if (password?.toIntOrNull() == null) return false

    return true
}
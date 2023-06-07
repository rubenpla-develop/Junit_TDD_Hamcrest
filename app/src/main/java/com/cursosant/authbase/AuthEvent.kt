package com.cursosant.authbase

enum class AuthEvent {
    USER_EXISTS,
    USER_NOT_EXISTS,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    EMPTY_FORM,
    UNKNOWN_EVENT
}
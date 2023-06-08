package com.cursosant.authbase

enum class AuthEvent {
    USER_EXISTS,
    USER_NOT_EXISTS,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    EMPTY_FORM,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_USER,
    NULL_EMAIL,
    UNKNOWN_EVENT
}
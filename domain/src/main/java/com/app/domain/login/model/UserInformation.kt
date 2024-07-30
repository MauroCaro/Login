package com.app.domain.login.model

data class UserInformation(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isUserActive: Boolean
)
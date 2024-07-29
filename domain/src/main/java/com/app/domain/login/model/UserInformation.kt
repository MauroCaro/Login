package com.app.domain.login.model

data class UserInformation(
    val id: String,
    val name: String,
    val lastName: String,
    val age: Int,
    val address: String,
    val isUserActive: Boolean
)
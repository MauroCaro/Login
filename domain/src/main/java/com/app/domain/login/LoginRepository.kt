package com.app.domain.login

import com.app.domain.login.model.UserInformation

interface LoginRepository {

    suspend fun checkLoginCredentials(userName: String, password: String): UserInformation?

}
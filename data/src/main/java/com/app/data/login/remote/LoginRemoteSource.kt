package com.app.data.login.remote

import com.app.data.common.user.model.UserPojo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRemoteSource @Inject constructor() {

    fun checkLoginCredentials(userName: String, password: String): List<UserPojo> {
        return TODO("Here would be the implementation for backend call")
    }
}
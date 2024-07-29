package com.app.domain.login.model

sealed class LoginState {

    object UserDoesNotExist : LoginState()

    object UserIsNotActive : LoginState()

    data class LoginInformation(val userInformation: UserInformation) : LoginState()

}
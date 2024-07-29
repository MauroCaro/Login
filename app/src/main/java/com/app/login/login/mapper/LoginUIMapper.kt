package com.app.login.login.mapper

import com.app.domain.login.model.LoginState
import com.app.login.login.model.LoginUIState
import javax.inject.Inject

class LoginUIMapper @Inject constructor() {

    fun mapToUI(loginState: LoginState): LoginUIState {
        return LoginUIState.Loading
    }

}
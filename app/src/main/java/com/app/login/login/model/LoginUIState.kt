package com.app.login.login.model

sealed class LoginUIState {

    data object Loading : LoginUIState()

}
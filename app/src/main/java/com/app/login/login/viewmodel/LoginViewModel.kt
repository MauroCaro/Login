package com.app.login.login.viewmodel

import com.app.domain.login.uc.DoLoginUC
import com.app.login.base.navigation.Destination
import com.app.login.base.navigation.Navigator
import com.app.login.base.viewmodel.BaseViewModel
import com.app.login.login.mapper.LoginUIMapper
import com.app.login.login.model.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUC: DoLoginUC,
    private val uiMapper: LoginUIMapper,
    private val appNavigator: Navigator
) : BaseViewModel<LoginUIState>(LoginUIState.Loading) {

    fun tryLogin(userName: String, password: String) = launchWithErrorHandlingDefault {
        doLoginUC.invoke(userName, password).collect {
            _event.emit(uiMapper.mapToUI(it))
        }
    }

    fun navigateToSignUp() = launchWithErrorHandlingDefault {
        appNavigator.navigateTo(
            Destination.SignUpScreen()
        )
    }
}
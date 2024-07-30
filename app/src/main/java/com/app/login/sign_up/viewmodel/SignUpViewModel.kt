package com.app.login.sign_up.viewmodel

import com.app.domain.login.model.UserInformation
import com.app.domain.login.uc.SignUpUC
import com.app.login.base.navigation.Navigator
import com.app.login.base.viewmodel.BaseViewModel
import com.app.login.login.model.LoginUIState
import com.app.login.sign_up.mapper.SignUpUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val appNavigator: Navigator,
    private val signUpUC: SignUpUC,
    private val signUpUIMapper: SignUpUIMapper,
) : BaseViewModel<LoginUIState>(LoginUIState.Loading) {

    fun signUp(userInformation: UserInformation) = launchWithErrorHandlingDefault {
        signUpUC.invoke(userInformation).let {
            _event.emit(signUpUIMapper.mapToUI(::navigateBack))
        }
    }

    private fun navigateBack() = launchWithErrorHandlingDefault {
        appNavigator.navigateBack()
    }
}
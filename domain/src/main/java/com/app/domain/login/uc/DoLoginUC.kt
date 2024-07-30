package com.app.domain.login.uc

import com.app.domain.login.LoginRepository
import com.app.domain.login.model.LoginState
import com.app.domain.login.model.UserInformation
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class DoLoginUC @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun invoke(userName: String, password: String): Flow<LoginState> {
        return loginRepository.checkLoginCredentials(userName, password).map { userInfo ->
            when {
                userInfo == null || userInfo.id.isNullOrEmpty() -> LoginState.UserDoesNotExist
                !userInfo.isUserActive -> LoginState.UserIsNotActive
                else -> LoginState.LoginInformation(userInfo)
            }
        }
    }
}
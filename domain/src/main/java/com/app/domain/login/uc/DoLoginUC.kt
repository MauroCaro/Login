package com.app.domain.login.uc

import com.app.domain.login.LoginRepository
import com.app.domain.login.model.LoginState
import com.app.domain.login.model.UserInformation
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class DoLoginUC @Inject constructor(
    private val loginRepository: LoginRepository
) {

    fun invoke(userName: String, password: String): Flow<LoginState> {
        val userInfo: UserInformation? = UserInformation("", "", "", 0, "", false)

        if (userInfo == null || userInfo.id.isEmpty()) {
            LoginState.UserDoesNotExist
        }

        if (userInfo!!.isUserActive.not()) {
            LoginState.UserIsNotActive
        }

        LoginState.UserIsNotActive
        return TODO("Provide the return value")
    }
}
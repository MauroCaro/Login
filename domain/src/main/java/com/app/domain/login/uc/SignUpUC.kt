package com.app.domain.login.uc

import com.app.domain.login.LoginRepository
import com.app.domain.login.model.UserInformation
import java.util.UUID
import javax.inject.Inject

class SignUpUC @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun invoke(userInformation: UserInformation) {
        loginRepository.createOrUpdate(
            if (userInformation.id == null) {
                userInformation.copy(id = UUID.randomUUID().toString())
            } else {
                userInformation
            }
        )
    }
}
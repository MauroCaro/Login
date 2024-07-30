package com.app.domain.login

import com.app.domain.data.repository.BaseRepository
import com.app.domain.login.model.UserInformation
import kotlinx.coroutines.flow.Flow

interface LoginRepository : BaseRepository<UserInformation>{

    suspend fun checkLoginCredentials(userName: String, password: String): Flow<UserInformation?>

}
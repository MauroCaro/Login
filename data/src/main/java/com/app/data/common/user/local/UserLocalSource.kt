package com.app.data.common.user.local

import com.app.data.base.local.BaseLocalSource
import com.app.data.common.user.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalSource : BaseLocalSource<UserEntity> {

    fun checkLoginCredentials(userName: String, password: String): Flow<UserEntity?>
}
package com.app.data.common.user.mapper

import com.app.data.base.mapper.Mapper
import com.app.data.common.user.model.UserEntity
import com.app.domain.login.model.UserInformation
import javax.inject.Inject

class UserDomainMapper @Inject constructor() : Mapper<UserInformation, UserEntity> {

    override fun mapRemote(remotePojo: UserEntity): UserInformation {
        TODO("Not yet implemented")
    }

}
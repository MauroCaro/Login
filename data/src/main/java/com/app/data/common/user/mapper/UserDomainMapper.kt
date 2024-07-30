package com.app.data.common.user.mapper

import com.app.data.base.mapper.Mapper
import com.app.data.common.user.model.UserEntity
import com.app.domain.login.model.UserInformation
import javax.inject.Inject

class UserDomainMapper @Inject constructor() : Mapper<UserInformation, UserEntity> {

    override fun mapRemote(entity: UserEntity): UserInformation {
       return UserInformation(
           entity.id,
           entity.firstName,
           entity.lastName,
           entity.email,
           entity.password,
           entity.isUserActive,
       )
    }

    override fun mapLocal(userInfo: UserInformation): UserEntity {
        return UserEntity(
            userInfo.id!!,
            userInfo.firstName,
            userInfo.lastName,
            userInfo.email,
            userInfo.password,
            userInfo.isUserActive
        )
    }
}
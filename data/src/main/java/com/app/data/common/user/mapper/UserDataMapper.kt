package com.app.data.common.user.mapper

import com.app.data.base.mapper.Mapper
import com.app.data.common.user.model.UserEntity
import com.app.data.common.user.model.UserPojo
import javax.inject.Inject

class UserDataMapper @Inject constructor() : Mapper<UserEntity, UserPojo> {

    override fun mapRemote(remotePojo: UserPojo): UserEntity {
        TODO("Not yet implemented")
    }

}
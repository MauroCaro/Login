package com.app.data.common.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.data.base.local.IdentifyEntity

@Entity
data class UserEntity(
    @PrimaryKey
    override val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isUserActive: Boolean
) : IdentifyEntity
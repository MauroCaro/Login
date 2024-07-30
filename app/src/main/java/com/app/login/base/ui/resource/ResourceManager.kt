package com.app.login.base.ui.resource

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes resId: Int): String
}
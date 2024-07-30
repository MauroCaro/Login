package com.app.login.login.mapper

import com.app.domain.login.model.LoginState
import com.app.login.R
import com.app.login.base.bottom_sheet.BottomSheetBuilder
import com.app.login.base.ui.fragment.ShowDialog
import com.app.login.base.ui.fragment.ViewEffect
import com.app.login.base.ui.resource.ResourceManager
import javax.inject.Inject

class LoginUIMapper @Inject constructor(
    private val resourceManager: ResourceManager
) {

    fun mapToUI(loginState: LoginState): ViewEffect {
        return when (loginState) {
            LoginState.UserDoesNotExist -> {
                ShowDialog(
                    BottomSheetBuilder(
                        icon = com.app.ui_commons.R.drawable.ic_warning,
                        title = resourceManager.getString(R.string.general_warning_title),
                        desc = resourceManager.getString(R.string.login_error_user_does_not_exist),
                    ).setNegativeButton(resourceManager.getString(R.string.dismiss))
                )
            }

            LoginState.UserIsNotActive -> {
                ShowDialog(
                    BottomSheetBuilder(
                        icon = com.app.ui_commons.R.drawable.ic_warning,
                        title = resourceManager.getString(R.string.general_warning_title),
                        desc = resourceManager.getString(R.string.login_error_user_is_not_active),
                    ).setNegativeButton(resourceManager.getString(R.string.dismiss))
                        .setPositiveButton(resourceManager.getString(R.string.general_contact_support))
                )
            }

            else -> {
                ShowDialog(
                    BottomSheetBuilder(
                        icon = com.app.ui_commons.R.drawable.ic_check,
                        title = resourceManager.getString(R.string.login_success_title),
                        desc = resourceManager.getString(R.string.login_success_message),
                    ).setNegativeButton(resourceManager.getString(R.string.dismiss))
                )

            }
        }
    }

}
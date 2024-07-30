package com.app.login.sign_up.mapper

import com.app.login.R
import com.app.login.base.bottom_sheet.BottomSheetBuilder
import com.app.login.base.ui.fragment.ShowDialog
import com.app.login.base.ui.resource.ResourceManager
import kotlinx.coroutines.Job
import javax.inject.Inject

class SignUpUIMapper @Inject constructor(
    private val resourceManager: ResourceManager,
) {

    fun mapToUI(navigateBack: () -> Unit): ShowDialog {
        return ShowDialog(
            BottomSheetBuilder(
                icon = com.app.ui_commons.R.drawable.ic_check,
                title = resourceManager.getString(R.string.register_success_title),
                desc = resourceManager.getString(R.string.register_success_message),
            ).setNegativeButton(resourceManager.getString(R.string.dismiss)) {
                navigateBack.invoke()
            }
        )
    }
}
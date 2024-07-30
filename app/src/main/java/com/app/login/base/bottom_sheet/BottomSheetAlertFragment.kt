package com.app.login.base.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.app.ui_commons.bottom_dialog.BottomSheetContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAlertFragment : BottomSheetDialogFragment() {

    private var negativeListener: OnClickListener? = null
    private var positiveListener: OnClickListener? = null
    private var dismissListener: OnClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val args = arguments?.getParcelable<BottomSheetAlertArgs>(EXTRA_BOTTOM_SHEET_ALERT)
                args?.let {
                    BottomSheetContent(
                        icon = it.icon,
                        title = it.title,
                        desc = it.description,
                        secondLine = it.secondLine,
                        closable = it.closable,
                        positiveButtonText = it.positiveButtonText,
                        positiveButtonOnClick = { dismiss() },
                        negativeButtonText = it.negativeButtonText,
                        negativeButtonOnClick = { dismiss() },
                        onCloseClick = { dismiss() }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_BOTTOM_SHEET_ALERT = "bottomSheetAlertArgs"

        fun newInstance(
            icon: Int?,
            title: String?,
            desc: String?,
            secondLine: String?,
            negativeButtonText: String?,
            negativeButton: OnClickListener?,
            positiveButtonText: String?,
            positiveButton: OnClickListener?,
            closable: Boolean = true,
            type: DialogButtonsType,
            hideDismissButton: Boolean = false,
            dismissListener: OnClickListener?,
            shouldNotifyNegativeListenerWhenClosing: Boolean = true,
        ) = BottomSheetAlertFragment().apply {
            positiveListener = positiveButton
            negativeListener = negativeButton
            this.dismissListener = dismissListener
            arguments =
                Bundle().apply {
                    putParcelable(
                        EXTRA_BOTTOM_SHEET_ALERT,
                        BottomSheetAlertArgs(
                            title = title,
                            icon = icon,
                            description = desc,
                            secondLine = secondLine,
                            negativeButtonText = negativeButtonText,
                            positiveButtonText = positiveButtonText,
                            closable = closable,
                            dialogButtonsType = type,
                            hideDismissButton = hideDismissButton,
                            shouldNotifyNegativeListenerWhenClosing = shouldNotifyNegativeListenerWhenClosing
                        ),
                    )
                }
        }
    }
}
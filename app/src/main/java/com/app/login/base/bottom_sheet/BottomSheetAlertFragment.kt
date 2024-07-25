package com.app.login.base.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAlertFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // MyBottomSheetContent()
            }
        }
    }

    companion object {

        const val EXTRA_BOTTOM_SHEET_ALERT = "bottomSheetAlertArgs"

        internal fun newInstance(
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
            lottieAnimationRes: Int?,
        ) = BottomSheetAlertFragment().apply {
        }
    }
}
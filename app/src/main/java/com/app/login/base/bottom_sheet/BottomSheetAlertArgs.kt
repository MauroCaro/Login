package com.app.login.base.bottom_sheet

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomSheetAlertArgs(
    @DrawableRes val icon: Int?,
    val title: String?,
    val description: String?,
    val secondLine: String?,
    val negativeButtonText: String?,
    val positiveButtonText: String?,
    val closable: Boolean,
    val dialogButtonsType: DialogButtonsType,
    val hideDismissButton: Boolean = false,
    val shouldNotifyNegativeListenerWhenClosing: Boolean = true,
    @RawRes val lottieAnimationRes: Int? = null,
) : Parcelable
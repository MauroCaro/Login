package com.app.login.base.ui.fragment

import com.app.login.base.bottom_sheet.BottomSheetBuilder

interface ViewEffect

object BackEffect : ViewEffect
object FinishEffect : ViewEffect

data class ShowDialog(
    val builder: BottomSheetBuilder,
) : ViewEffect



package com.app.login.base.bottom_sheet


typealias OnClickListener = () -> Unit

data class BottomSheetBuilder(
    var icon: Int? = null,
    var title: String?,
    var desc: String?,
    var closable: Boolean = true,
) {
    private var secondLine: String? = null
    private var negativeButtonText: String? = null
    private var positiveButtonText: String? = null
    private var negativeButton: OnClickListener? = null
    private var positiveButton: OnClickListener? = null
    private var type: DialogButtonsType = DialogButtonsType.INFORMATION
    private var hideDismissButton: Boolean = false
    private var shouldNotifyNegativeListenerWhenClosing: Boolean = false
    private var dismissListener: OnClickListener? = null
    private var lottieAnimationRes: Int? = null

    fun setTitle(title: String?) = apply { this.title = title }

    fun setDescription(desc: String?) = apply { this.desc = desc }

    fun setSecondLine(secondLine: String?) = apply { this.secondLine = secondLine }

    fun setHideDismissButton(hideDismissButton: Boolean) =
        apply { this.hideDismissButton = hideDismissButton }

    fun setShouldNotifyNegativeListenerWhenClosing(doNotNotify: Boolean) =
        apply { this.shouldNotifyNegativeListenerWhenClosing = doNotNotify }

    fun setButtonType(type: DialogButtonsType) = apply { this.type = type }

    fun withAnimation(lottieAnimationRes: Int) =
        apply {
            this.lottieAnimationRes = lottieAnimationRes
        }

    fun setDismissListener(onclick: OnClickListener? = null) =
        apply { this.dismissListener = onclick }

    fun setNegativeButton(
        negativeButtonText: String?,
        action: OnClickListener? = null,
    ) = apply {
        this.negativeButtonText = negativeButtonText
        this.negativeButton = action
    }

    fun setPositiveButton(
        positiveButtonText: String?,
        action: OnClickListener? = null,
    ) = apply {
        this.positiveButtonText = positiveButtonText
        this.positiveButton = action
    }

    fun setClosable(isClosable: Boolean) =
        apply {
            closable = isClosable
        }

    fun build() =
        BottomSheetAlertFragment.newInstance(
            icon,
            title,
            desc,
            secondLine,
            negativeButtonText,
            negativeButton,
            positiveButtonText,
            positiveButton,
            closable,
            type,
            hideDismissButton,
            dismissListener,
            shouldNotifyNegativeListenerWhenClosing
        )
}
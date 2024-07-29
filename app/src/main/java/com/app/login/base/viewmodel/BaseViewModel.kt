package com.app.login.base.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.login.base.bottom_sheet.BottomSheetBuilder
import com.app.login.base.bottom_sheet.DialogButtonsType
import com.app.login.base.navigation.Navigator
import com.app.login.base.ui.fragment.ShowDialog
import com.app.login.base.ui.fragment.ViewEffect
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BaseViewModel<State> @Inject constructor(
    initialState: State
) : ViewModel() {

    protected val _event = MutableSharedFlow<ViewEffect>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    val event: SharedFlow<ViewEffect> = _event.asSharedFlow()

    val _state = MutableStateFlow<State>(initialState)

    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(10000),
        initialState,
    )

    fun launchWithErrorHandlingDefault(
        fallback: (() -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit,
    ): Job =
        this.viewModelScope.launch(
            context =
            CoroutineExceptionHandler { _, exception ->
                try {
                    Log.e(state.value.toString(), exception.message.toString())
                    fallback?.invoke() ?: run {
                        _event.tryEmit(
                            ShowDialog(
                                BottomSheetBuilder(
                                    title = "",
                                    desc = ""
                                ).setButtonType(DialogButtonsType.INFORMATION)
                            )
                        )
                    }
                } catch (reportException: Exception) {
                    Log.e(state.value.toString(), exception.message.toString())
                }
            } + Dispatchers.Default,
            block = block,
        )

    suspend fun <T> MutableStateFlow<T>.setValueInMain(value: T) {
        withContext(Dispatchers.Main) {
            this@setValueInMain.value = value
        }
    }
}
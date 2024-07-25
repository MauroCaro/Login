package com.app.login.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.login.base.ui.ViewEffect
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn

class BaseViewModel<State>(initialState: State) : ViewModel() {

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
}
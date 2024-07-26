package com.app.login.base.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.app.login.base.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    navigator: Navigator
) : ViewModel() {

    val navigationChannel = navigator.navigationChannel
}
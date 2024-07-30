package com.app.login.base.ui.compose

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.app.login.base.ui.fragment.ShowDialog
import com.app.login.base.viewmodel.BaseViewModel

@Composable
fun ViewEventHost(viewModel: BaseViewModel<*>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ShowDialog -> {
                    if (context is AppCompatActivity) {
                        event.builder.build().show(context.supportFragmentManager, "BottomSheetAlert")
                    }
                }
            }
        }
    }
}
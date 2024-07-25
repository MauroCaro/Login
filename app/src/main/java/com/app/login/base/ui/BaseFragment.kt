package com.app.login.base.ui

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.app.login.base.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseFragment<State> : Fragment() {


    abstract fun viewModel(): BaseViewModel<State>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() = lifecycleScope.launch {
        viewModel().event.onEach {
            processEvents(it)
        }.launchIn(this)
    }

    protected fun processEvents(it: ViewEffect) {
        when (it) {
            is FinishEffect -> {
                requireActivity().finish()
            }

            is BackEffect -> {
                requireActivity().onBackPressed()
            }

            is ShowDialog -> {

            }

            else -> {
                onNewViewEffect(it)
            }
        }
    }

    @Composable
    abstract fun ComposableUI(state: State)

    open fun onNewViewEffect(viewEffect: ViewEffect) {
        // Override when required
    }
}
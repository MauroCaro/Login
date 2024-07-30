package com.app.login.base.ui.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.login.base.navigation.Destination
import com.app.login.base.navigation.NavHost
import com.app.login.base.navigation.NavigationIntent
import com.app.login.base.navigation.composable
import com.app.login.login.compose.LoginScreen
import com.app.login.login.viewmodel.LoginViewModel
import com.app.login.sign_up.compose.SignUpScreen
import com.app.login.sign_up.viewmodel.SignUpViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


@Composable
fun NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.LoginScreen
    ) {
        composable(destination = Destination.LoginScreen) {
            LoginScreen()
            val loginViewModelL: LoginViewModel = hiltViewModel()
            ViewEventHost(loginViewModelL)
        }
        composable(destination = Destination.HomeScreen) {
            //Navigate to HOME
        }
        composable(destination = Destination.SignUpScreen) {
            SignUpScreen()
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            ViewEventHost(signUpViewModel)
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}
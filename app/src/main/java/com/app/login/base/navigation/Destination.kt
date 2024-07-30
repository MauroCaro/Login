package com.app.login.base.navigation

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object LoginScreen : NoArgumentsDestination(LOGIN_ROUTE)

    object SignUpScreen : NoArgumentsDestination(SIGN_UP_ROUTE)

    object HomeScreen : Destination(HOME_ROUTE, ID) {
        operator fun invoke(idUser: String): String = route.appendParams(
            ID to idUser
        )
    }

    companion object {
        private const val HOME_ROUTE = "home"
        private const val SIGN_UP_ROUTE = "SignUp"
        private const val LOGIN_ROUTE = "Login"
        private const val ID = "id"
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}
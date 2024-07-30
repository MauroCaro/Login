package com.app.domain.login

import com.app.domain.login.model.LoginState
import com.app.domain.login.model.UserInformation
import com.app.domain.login.uc.DoLoginUC
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DoLoginUCTest {

    private val loginRepository = mockk<LoginRepository>()
    private val doLoginUC = DoLoginUC(loginRepository)

    @Test
    fun `invoke should return LoginInformation when user exists and is active`() = runTest {
        val user = UserInformation("123", "John Doe", "john.doe@example.com", 12345, "active", true)
        coEvery { loginRepository.checkLoginCredentials("john.doe@example.com", "password") } returns flowOf(user)
        val result = doLoginUC.invoke("john.doe@example.com", "password").first()
        assertEquals(LoginState.LoginInformation(user), result)
    }

    @Test
    fun `invoke should return UserDoesNotExist when user does not exist`() = runTest {
        coEvery { loginRepository.checkLoginCredentials("nonexistent@example.com", "password") } returns flowOf(null)
        val result = doLoginUC.invoke("nonexistent@example.com", "password").first()
        assertEquals(LoginState.UserDoesNotExist, result)
    }

    @Test
    fun `invoke should return UserIsNotActive when user exists but is not active`() = runTest {
        val inactiveUser = UserInformation("456", "Inactive User", "inactive@example.com", 98765, "inactive", false)
        coEvery { loginRepository.checkLoginCredentials("inactive@example.com", "password") } returns flowOf(inactiveUser)
        val result = doLoginUC.invoke("inactive@example.com", "password").first()
        assertEquals(LoginState.UserIsNotActive, result)
    }
}

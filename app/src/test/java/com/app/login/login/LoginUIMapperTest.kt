package com.app.login.login

import com.app.domain.login.model.LoginState
import com.app.domain.login.model.UserInformation
import com.app.login.base.ui.fragment.ShowDialog
import com.app.login.base.ui.resource.ResourceManager
import com.app.login.login.mapper.LoginUIMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.*
import com.app.login.R

class LoginUIMapperTest {

    private val resourceManager = mockk<ResourceManager>()
    private val mapper = LoginUIMapper(resourceManager)

    @Test
    fun `UserDoesNotExist returns correct ShowDialog event`() {
        // Setup
        val expectedTitle = "Warning" // Example string
        val expectedDesc = "User does not exist"
        val expectedNegativeButtonText = "Dismiss"

        every { resourceManager.getString(R.string.general_warning_title) } returns expectedTitle
        every { resourceManager.getString(R.string.login_error_user_does_not_exist) } returns expectedDesc
        every { resourceManager.getString(R.string.dismiss) } returns expectedNegativeButtonText

        // Action
        val result = mapper.mapToUI(LoginState.UserDoesNotExist)

        // Assert
        assertTrue(result is ShowDialog)
        val showDialog = result as ShowDialog
        assertEquals(com.app.ui_commons.R.drawable.ic_warning, showDialog.builder.icon)
        assertEquals(expectedTitle, showDialog.builder.title)
        assertEquals(expectedDesc, showDialog.builder.desc)
    }

    @Test
    fun `UserIsNotActive returns correct ShowDialog event`() {
        // Setup
        val expectedTitle = "Warning"
        val expectedDesc = "User is not active"
        val expectedNegativeButtonText = "Dismiss"
        val expectedPositiveButtonText = "Contact Support"

        every { resourceManager.getString(R.string.general_warning_title) } returns expectedTitle
        every { resourceManager.getString(R.string.login_error_user_is_not_active) } returns expectedDesc
        every { resourceManager.getString(R.string.dismiss) } returns expectedNegativeButtonText
        every { resourceManager.getString(R.string.general_contact_support) } returns expectedPositiveButtonText

        // Action
        val result = mapper.mapToUI(LoginState.UserIsNotActive)

        // Assert
        assertTrue(result is ShowDialog)
        val showDialog = result as ShowDialog
        assertEquals(com.app.ui_commons.R.drawable.ic_warning, showDialog.builder.icon)
        assertEquals(expectedTitle, showDialog.builder.title)
        assertEquals(expectedDesc, showDialog.builder.desc)
    }

    @Test
    fun `LoginSuccess returns correct ShowDialog event with user information`() {
        // Setup
        val expectedTitle = "Success"
        val expectedDesc = "Login successful"
        val expectedNegativeButtonText = "Dismiss"
        val userInformation = UserInformation(
            id = "123",
            name = "John",
            lastName = "Doe",
            age = 30,
            address = "123 Main St",
            isUserActive = true
        )

        every { resourceManager.getString(R.string.login_success_title) } returns expectedTitle
        every { resourceManager.getString(R.string.login_success_message) } returns expectedDesc
        every { resourceManager.getString(R.string.dismiss) } returns expectedNegativeButtonText

        // Action
        val result = mapper.mapToUI(LoginState.LoginInformation(userInformation))

        // Assert
        assertTrue(result is ShowDialog)
        val showDialog = result as ShowDialog
        assertEquals(com.app.ui_commons.R.drawable.ic_check, showDialog.builder.icon)
        assertEquals(expectedTitle, showDialog.builder.title)
    }
}
package com.app.login.login

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.login.base.navigation.Destination
import com.app.login.base.ui.activity.MainActivity
import com.app.login.login.compose.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun testLoginScreenInputAndButton() {


        val testUsername = "test@example.com"
        val testPassword = "password"

        composeTestRule.onNodeWithTag("userName") // This should match the stringResource for username label
            .performTextInput(testUsername)
        composeTestRule.onNodeWithTag("password") // This should match the stringResource for password label
            .performTextInput(testPassword)

        composeTestRule.onNodeWithTag("loginButton") // This should match the stringResource for the login button
            .assertIsDisplayed()
            .performClick()
    }
}
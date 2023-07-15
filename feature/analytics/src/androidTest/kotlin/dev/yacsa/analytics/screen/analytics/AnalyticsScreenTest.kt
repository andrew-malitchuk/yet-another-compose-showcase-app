package dev.yacsa.analytics.screen.analytics

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.yacsa.ui.theme.YacsaTheme
import org.junit.Rule
import org.junit.Test

class AnalyticsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            YacsaTheme() {
                AnalyticsScreen(
                    uiState = AnalyticsUiState(),
                    onBackClick = {},
                    onDeleteClick = {},
                )
            }
        }

        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.onNodeWithText("Welcome").assertIsDisplayed()
    }

}
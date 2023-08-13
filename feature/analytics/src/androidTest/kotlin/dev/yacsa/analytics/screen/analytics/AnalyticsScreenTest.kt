package dev.yacsa.analytics.screen.analytics

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dev.yacsa.model.model.analytics.AnalyticsUiModel
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
                    uiState = AnalyticsUiState(
                        analytics = listOf(
                            AnalyticsUiModel(
                                "foo",
                                "bar",
                            ),
                        ),
                    ),
                    onBackClick = {},
                    onDeleteClick = {},
                )
            }
        }

//        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.onNodeWithTag("fab").assertIsDisplayed()
        composeTestRule.onNodeWithTag("bar").onChildAt(0).assertExists()
        composeTestRule.onNodeWithTag("fab").performClick()
    }
}

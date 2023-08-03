package dev.yacsa.main.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.yacsa.main.screen.main.MainActivity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDeepLinkingTest {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
        putParcelableArrayListExtra("CROP_URIS", ArrayList())
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<MainActivity>(intent)
//    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<dev.yacsa.main.screen.main.MainActivity>()

    private lateinit var scenario: ActivityScenario<dev.yacsa.main.screen.main.MainActivity>

    @Before
    fun setup() {
        scenario = activityScenarioRule.scenario
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun deepLinkApplicationTextDashboardDeeplinkHasText() {
        // Launch the activity with the deep link
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("yacsa://search")
        }
        scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        // Verify that the activity displays the expected text
        val value = composeTestRule.onNodeWithTag("dashboardDeeplinkArgument")
        value.assertTextEquals("This is Home Screen johnDoe")
        for ((key, value) in value.fetchSemanticsNode().config) {
            if (key.name == "EditableText") {
                assertEquals("This is Home Screen johnDoe", value.toString())
            }
        }
    }

}
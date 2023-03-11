package dev.yacsa.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import dev.yacsa.ui.theme.YacsaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val isDarkTheme = true

        setContent {
            YacsaTheme(
                isDarkTheme
            ) {
                Text(text = "foobar", color = YacsaTheme.colors.primaryText)
            }
        }
    }

}
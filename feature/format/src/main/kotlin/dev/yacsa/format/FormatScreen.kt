package dev.yacsa.format

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.Text

@Composable
fun FormatScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("FormatScreen")
        Button(onClick = {
        }) {
            Text(text = "To books")
        }
    }
}
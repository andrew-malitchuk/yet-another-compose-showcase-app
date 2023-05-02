package dev.yacsa.ui.composable.chip

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    defaultColor: Color,
    selectedColor: Color,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) selectedColor else defaultColor,
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(name)
                    },
                ),
        ) {
            Text(
                text = name,
                color = Color.White,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Preview
@Composable
fun Preview_Chip() {
    Chip(
        isSelected = true,
        defaultColor = Color.DarkGray,
        selectedColor = Color.DarkGray,
    )
}

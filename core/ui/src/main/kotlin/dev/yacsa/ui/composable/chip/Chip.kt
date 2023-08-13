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
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    defaultColor: Color,
    selectedColor: Color,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(YacsaTheme.spacing.extraSmall),
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
                modifier = Modifier.padding(YacsaTheme.spacing.small),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Chip_Light() {
    YacsaTheme(false) {
        Chip(
            isSelected = true,
            defaultColor = Color.DarkGray,
            selectedColor = Color.DarkGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Chip_Dark() {
    YacsaTheme(true) {
        Chip(
            isSelected = true,
            defaultColor = Color.DarkGray,
            selectedColor = Color.DarkGray,
        )
    }
}

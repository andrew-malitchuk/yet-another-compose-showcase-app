package dev.yacsa.search.screen.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.composable.chip.Chip

@Composable
fun ChipGroup(
    values: List<String>,
    defaultColor: Color,
    selectedColor: Color,
    onSelectedChanged: (String) -> Unit = {},
    onDelete: () -> Unit
) {
    LazyRow(
        // content padding
        contentPadding = PaddingValues(
            horizontal = 8.dp
        )
    ) {
        items(values) {
            Chip(
                name = it,
                isSelected = false,
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
                defaultColor = defaultColor,
                selectedColor = selectedColor
            )
        }
        if (!values.isNullOrEmpty()) {
            item {
                Chip(
                    name="❌",
                    isSelected = false,
                    onSelectionChanged = {
                        onDelete()
                    },
                    defaultColor = defaultColor,
                    selectedColor = selectedColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ChipGroup() {
    ChipGroup(
        values = listOf("Foo", "Bar"),
        defaultColor = Color.DarkGray,
        selectedColor = Color.Cyan,
        onDelete = {}
    )
}
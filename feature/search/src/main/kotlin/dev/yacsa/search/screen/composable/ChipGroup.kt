package dev.yacsa.search.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ChipGroup(
    values: List<String>,
    defaultColor: Color,
    selectedColor: Color,
    onSelectedChanged: (String) -> Unit = {},
    onDelete: () -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // content padding
        contentPadding = PaddingValues(
            horizontal = 8.dp,
        ),
    ) {
        items(values) {
            AssistChip(
                onClick = {
                    onSelectedChanged(it)
                },
                label = {
                    Text(
                        text = it,
                        color = YacsaTheme.colors.primaryText,
                    )
                },
            )
        }
        if (!values.isNullOrEmpty()) {
            item {
                AssistChip(
                    onClick = {
                        onDelete()
                    },
                    label = {
                        Text(
                            // TODO: Fix
                            text = "Delete",
                            color = YacsaTheme.colors.primaryText,
                        )
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(id = R.drawable.icon_trash_regular_16), contentDescription = null)
                    },
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
        onDelete = {},
    )
}

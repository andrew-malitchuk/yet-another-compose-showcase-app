package dev.yacsa.search.screen.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGroup(
    values: List<String>,
    defaultColor: Color,
    selectedColor: Color,
    onSelectedChanged: (String) -> Unit = {},
    onDelete: (() -> Unit)? = null,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
        // content padding
        contentPadding = PaddingValues(
            horizontal = YacsaTheme.spacing.small,
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
                        color = YacsaTheme.colors.primary,
                    )
                },
            )
        }
        if (!values.isNullOrEmpty() && onDelete != null) {
            item {
                AssistChip(
//                    border =
                    colors = androidx.compose.material3.AssistChipDefaults.assistChipColors(
                        containerColor = YacsaTheme.colors.background,
                        labelColor = YacsaTheme.colors.primary,
                        leadingIconContentColor = YacsaTheme.colors.primary
                    ),
                    onClick = {
                        onDelete()
                    },
                    label = {
                        Text(
                            // TODO: Fix
                            text = "Delete",
                            color = YacsaTheme.colors.primary,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_trash_regular_16),
                            contentDescription = null,
                            tint = YacsaTheme.colors.primary,
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ChipGroup_Light() {
    YacsaTheme(false) {
        ChipGroup(
            values = listOf("Foo", "Bar"),
            defaultColor = Color.DarkGray,
            selectedColor = Color.Cyan,
            onDelete = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ChipGroup_Dark() {
    YacsaTheme(true) {
        ChipGroup(
            values = listOf("Foo", "Bar"),
            defaultColor = Color.DarkGray,
            selectedColor = Color.Cyan,
            onDelete = {},
        )
    }
}

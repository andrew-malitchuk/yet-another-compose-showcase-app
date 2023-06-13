package dev.yacsa.widget.favourite

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxWidth
import dev.yacsa.model.model.BookUiModel


@Composable
fun ItemFetchedList(
    book: BookUiModel,
    onItemContentClick: () -> Unit,
    onFavouriteMark: (Int, Boolean) -> Unit,
) {

    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .clickable {
                onItemContentClick()
            }
            .background(Color(0xFFFFFFFF))
            .cornerRadius(8.dp)
    ) {

    }

}
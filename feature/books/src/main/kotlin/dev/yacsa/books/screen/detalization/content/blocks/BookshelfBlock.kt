package dev.yacsa.books.screen.detalization.content.blocks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.books.screen.list.item.ItemBookshelf
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun BookshelfBlock(
    book: BookUiModel,
    onBookshelfClick:(String)->Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Bookshelf",
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small)
        )
        book.bookshelves?.forEach { lang ->
            lang?.let {
                ItemBookshelf(bookshelf = it) {
                        onBookshelfClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_BookshelfBlock_Light() {
    YacsaTheme(false) {
        BookshelfBlock(
            book = BookUiModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true
            ),
            onBookshelfClick={}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_BookshelfBlock_Dark() {
    YacsaTheme(true) {
        BookshelfBlock(
            book = BookUiModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true
            ),
            onBookshelfClick={}
        )
    }
}
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
import androidx.compose.ui.unit.dp
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
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(6.dp)
        )
        book.bookshelves?.forEach { lang ->
            lang?.let {
                ItemBookshelf(bookshelf = it) {
                        onBookshelfClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_BookshelfBlock() {
    YacsaTheme {
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
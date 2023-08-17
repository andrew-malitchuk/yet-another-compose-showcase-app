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
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@Composable
fun BookshelfBlock(
    book: BookUiModel,
    onBookshelfClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = UiText.StringResource(dev.yacsa.localization.R.string.detalization_bookshelf).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small),
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
    val faker = Faker()
    YacsaTheme(false) {
        BookshelfBlock(
            book = BookUiModel(
                faker.hashCode(),
                faker.quote.fortuneCookie(),
                listOf(faker.quote.fortuneCookie()),
                emptyList(),
                emptyList(),
                listOf(faker.quote.fortuneCookie()),
                listOf(faker.quote.fortuneCookie()),
                true,
                faker.quote.fortuneCookie(),
                null,
                faker.hashCode(),
                true,
            ),
            onBookshelfClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_BookshelfBlock_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        BookshelfBlock(
            book = BookUiModel(
                faker.hashCode(),
                faker.quote.fortuneCookie(),
                listOf(faker.quote.fortuneCookie()),
                emptyList(),
                emptyList(),
                listOf(faker.quote.fortuneCookie()),
                listOf(faker.quote.fortuneCookie()),
                true,
                faker.quote.fortuneCookie(),
                null,
                faker.hashCode(),
                true,
            ),
            onBookshelfClick = {},
        )
    }
}

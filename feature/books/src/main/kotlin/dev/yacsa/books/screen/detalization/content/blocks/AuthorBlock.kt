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
import dev.yacsa.books.screen.list.item.ItemAuthor
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@Composable
fun AuthorBlock(
    book: BookUiModel,
    onAuthorClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = UiText.StringResource(dev.yacsa.localization.R.string.detalization_authors).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small),
        )
        book.authors?.forEach { person ->
            person?.let {
                ItemAuthor(person = it) {
                    it.name?.let { it1 -> onAuthorClick(it1) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthorBlock_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        AuthorBlock(
            book = BookUiModel(
                faker.hashCode(),
                faker.quote.fortuneCookie(),
                listOf( faker.quote.fortuneCookie()),
                emptyList(),
                emptyList(),
                listOf( faker.quote.fortuneCookie()),
                listOf( faker.quote.fortuneCookie()),
                true,
                faker.quote.fortuneCookie(),
                null,
                faker.hashCode(),
                true,
            ),
            onAuthorClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AuthorBlock_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        AuthorBlock(
            book = BookUiModel(
                faker.hashCode(),
                faker.quote.fortuneCookie(),
                listOf( faker.quote.fortuneCookie()),
                emptyList(),
                emptyList(),
                listOf( faker.quote.fortuneCookie()),
                listOf( faker.quote.fortuneCookie()),
                true,
                faker.quote.fortuneCookie(),
                null,
                faker.hashCode(),
                true,
            ),
            onAuthorClick = {},
        )
    }
}

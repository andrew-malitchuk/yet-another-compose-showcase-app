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
import dev.yacsa.books.screen.list.item.ItemLanguage
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@Composable
fun LanguageBlock(
    book: BookUiModel,
    onLanguageClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = UiText.StringResource(dev.yacsa.localization.R.string.search_language).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small),
        )
        book.languages?.forEach { lang ->
            lang?.let {
                ItemLanguage(lang = it) {
                    onLanguageClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LanguageBlock_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        LanguageBlock(
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
            onLanguageClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LanguageBlock_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        LanguageBlock(
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
            onLanguageClick = {},
        )
    }
}

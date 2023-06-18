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

@Composable
fun TranslatorsBlock(
    book: BookUiModel,
    onTranslatorClick:(String)->Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = UiText.StringResource(dev.yacsa.localization.R.string.detalization_translators).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small)
        )
        book.translators?.forEach { person ->
            person?.let {
                ItemAuthor(person = it) {
                    it.name?.let { it1 -> onTranslatorClick(it1) }
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_TranslatorsBlock_Light() {
    YacsaTheme(false) {
        TranslatorsBlock(
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
            onTranslatorClick={}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_TranslatorsBlock_Dark() {
    YacsaTheme(true) {
        TranslatorsBlock(
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
            onTranslatorClick={}
        )
    }
}
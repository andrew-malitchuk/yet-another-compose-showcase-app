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
import dev.yacsa.books.screen.list.item.ItemLanguage
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun LanguageBlock(
    book: BookUiModel,
    onLanguageClick:(String)->Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Languages",
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(6.dp)
        )
        book.languages?.forEach { lang ->
            lang?.let {
                ItemLanguage(lang = it) {
                    onLanguageClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_LanguageBlock() {
    YacsaTheme {
        LanguageBlock(
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
            onLanguageClick ={}
        )
    }
}
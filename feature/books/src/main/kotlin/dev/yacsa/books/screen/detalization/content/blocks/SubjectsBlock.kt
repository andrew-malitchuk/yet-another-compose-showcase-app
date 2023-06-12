package dev.yacsa.books.screen.detalization.content.blocks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.books.screen.list.item.ItemSubject
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubjectsBlock(
    book: BookUiModel,
    onSubjectClick:(String)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Subjects",
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small)
        )
        book.subjects?.forEach { subject ->
            subject?.let {
                ItemSubject(subject = it) {
                    onSubjectClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_SubjectsBlock_Light() {
    YacsaTheme(false) {
        SubjectsBlock(
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
            onSubjectClick={}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SubjectsBlock_Dark() {
    YacsaTheme(true) {
        SubjectsBlock(
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
            onSubjectClick={}
        )
    }
}
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
import dev.yacsa.books.screen.list.item.ItemFormat
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@Composable
fun FormatsBlock(
    book: BookUiModel,
    onFormatClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = UiText.StringResource(dev.yacsa.localization.R.string.detalization_formats).asString(),
            style = YacsaTheme.typography.title,
            color = YacsaTheme.colors.primary,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(YacsaTheme.spacing.small),
        )
        book.formats?.let { format ->
            format.applicationEpubZip?.let {
                ItemFormat(type = ".epub", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.applicationOctetStream?.let {
                ItemFormat(type = "octet-stream", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.applicationRdfXml?.let {
                ItemFormat(type = ".rdf", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.applicationxMobipocketEbook?.let {
                ItemFormat(type = ".mobi", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.imageJpeg?.let {
                ItemFormat(type = ".jpeg", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.textHtml?.let {
                ItemFormat(type = ".html", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.textPlain?.let {
                ItemFormat(type = ".txt", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
            format.textplainCharsetusAscii?.let {
                ItemFormat(type = "ASCII", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_FormatsBlock_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        FormatsBlock(
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
            onFormatClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_FormatsBlock_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        FormatsBlock(
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
            onFormatClick = {},
        )
    }
}

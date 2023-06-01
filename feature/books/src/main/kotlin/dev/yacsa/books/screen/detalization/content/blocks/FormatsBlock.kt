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
import dev.yacsa.books.screen.list.item.ItemFormat
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme


@Composable
fun FormatsBlock(
    book: BookUiModel,
    onFormatClick:(String)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Formats",
            style = YacsaTheme.typography.title,
            maxLines = 1,
            minLines = 1,
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier.height(6.dp)
        )
        book.formats?.let {format->
            format.applicationEpubZip?.let{
                ItemFormat(type = ".epub", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.applicationOctetStream?.let{
                ItemFormat(type = "octet-stream", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.applicationRdfXml?.let{
                ItemFormat(type = ".rdf", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.applicationxMobipocketEbook?.let{
                ItemFormat(type = ".mobi", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.imageJpeg?.let{
                ItemFormat(type = ".jpeg", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.textHtml?.let{
                ItemFormat(type = ".html", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.textPlain?.let{
                ItemFormat(type = ".txt", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            format.textplainCharsetusAscii?.let{
                ItemFormat(type = "ASCII", link = it) {
                    onFormatClick(it)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_FormatsBlock() {
    YacsaTheme {
        FormatsBlock(
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
            onFormatClick = {}
        )
    }
}
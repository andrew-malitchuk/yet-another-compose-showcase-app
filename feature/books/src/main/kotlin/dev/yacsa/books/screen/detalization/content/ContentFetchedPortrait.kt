package dev.yacsa.books.screen.detalization.content

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.books.screen.detalization.composable.toolbar.CollapsingToolbar
import dev.yacsa.books.screen.detalization.content.blocks.AuthorBlock
import dev.yacsa.books.screen.detalization.content.blocks.FormatsBlock
import dev.yacsa.books.screen.detalization.content.blocks.LanguageBlock
import dev.yacsa.books.screen.detalization.content.blocks.SubjectsBlock
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentFetchedPortrait(
    modifier: Modifier = Modifier,
    book: BookUiModel?,
    onBackClick: () -> Unit,
    onFormatClick: (String) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    with(systemUiController) {
        setSystemBarsColor(Color(0xFFE0DFFD))
        setNavigationBarColor(Color.White)
    }
    val lazyScrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            if (book != null) {
                CollapsingToolbar(
                    lazyScrollState,
                    book,
                    onBackClick,
                    {
                        coroutineScope.launch {
                            lazyScrollState.scrollToItem(4, 0)
                        }
                    }
                )
            }
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            AnimatedDivider(state = lazyScrollState)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .animateContentSize(),
                state = lazyScrollState,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (!book?.authors.isNullOrEmpty()) {
                    item {
                        book?.let { AuthorBlock(it) }
                    }
                }
                if (!book?.languages.isNullOrEmpty()) {
                    item {
                        book?.let { LanguageBlock(it) }
                    }
                }
                if (!book?.subjects.isNullOrEmpty()) {
                    item {
                        book?.let { SubjectsBlock(it) }
                    }
                }
                if (book?.formats != null) {
                    item {
                        book?.let {
                            FormatsBlock(
                                book = it,
                                onFormatClick = {
                                    onFormatClick(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetchedPortrait() {
    YacsaTheme {
        ContentFetchedPortrait(book = null, onBackClick = {}, onFormatClick = {})
    }
}

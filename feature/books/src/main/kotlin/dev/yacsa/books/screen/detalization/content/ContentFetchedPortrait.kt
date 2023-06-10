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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yacsa.books.screen.detalization.composable.toolbar.CollapsingToolbar
import dev.yacsa.books.screen.detalization.content.blocks.AuthorBlock
import dev.yacsa.books.screen.detalization.content.blocks.BookshelfBlock
import dev.yacsa.books.screen.detalization.content.blocks.FormatsBlock
import dev.yacsa.books.screen.detalization.content.blocks.LanguageBlock
import dev.yacsa.books.screen.detalization.content.blocks.SubjectsBlock
import dev.yacsa.books.screen.detalization.content.blocks.TranslatorsBlock
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.composable.divider.AnimatedDivider
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentFetchedPortrait(
    book: BookUiModel?,
    onBackClick: () -> Unit,
    onFormatClick: (String) -> Unit,
    onAuthorClick: (String) -> Unit,
    onTranslatorClick: (String) -> Unit,
    onLanguageClick: (String) -> Unit,
    onSubjectClick: (String) -> Unit,
    onBookshelfClick: (String) -> Unit,
    favourite: MutableState<Boolean?>,
) {
    val lazyScrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(YacsaTheme.colors.background),
        topBar = {
            if (book != null) {
                CollapsingToolbar(
                    lazyScrollState,
                    book,
                    onBackClick,
                    {
                        coroutineScope.launch {
                            lazyScrollState.scrollToItem(7, 0)
                        }
                    },
                    favourite
                )
            }
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).background(YacsaTheme.colors.background)) {
            AnimatedDivider(state = lazyScrollState)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(YacsaTheme.colors.background)
                    .animateContentSize(),
                state = lazyScrollState,
                contentPadding = PaddingValues(
                    horizontal = YacsaTheme.spacing.medium,
                    vertical = YacsaTheme.spacing.small
                ),
                verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
            ) {
                if (!book?.authors.isNullOrEmpty()) {
                    item {
                        book?.let { AuthorBlock(it, onAuthorClick) }
                    }
                }
                if (!book?.translators.isNullOrEmpty()) {
                    item {
                        book?.let { TranslatorsBlock(it, onTranslatorClick) }
                    }
                }
                if (!book?.languages.isNullOrEmpty()) {
                    item {
                        book?.let { LanguageBlock(it, onLanguageClick) }
                    }
                }
                if (!book?.subjects.isNullOrEmpty()) {
                    item {
                        book?.let { SubjectsBlock(it, onSubjectClick) }
                    }
                }
                if (!book?.bookshelves.isNullOrEmpty()) {
                    item {
                        book?.let { BookshelfBlock(it, onBookshelfClick) }
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
        ContentFetchedPortrait(
            book = null,
            onBackClick = {},
            {},
            {},
            {},
            {},
            {},
            {},
            remember { mutableStateOf(false) }
        )
    }
}

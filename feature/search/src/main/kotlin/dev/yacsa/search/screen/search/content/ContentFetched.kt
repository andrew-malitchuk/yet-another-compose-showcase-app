package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.yacsa.search.screen.composable.ChipGroup
import dev.yacsa.search.screen.composable.SearchToolbar
import dev.yacsa.search.screen.search.SearchUiState
import dev.yacsa.search.screen.search.content.result.ResultFetched
import dev.yacsa.search.screen.search.content.result.ResultIsLoading
import dev.yacsa.search.screen.search.dialog.FilterDialog
import dev.yacsa.search.screen.search.dialog.FilterDialogResult
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentNoData
import dev.yacsa.ui.composable.keyboard.clearFocusOnKeyboardDismiss
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier,
    searchText: String,
    onValueChange: (String) -> Unit,
    uiState: SearchUiState,
    onBookClicked: (Int) -> Unit,
    onDelete: () -> Unit,
    onFilterChanged: (FilterDialogResult) -> Unit,
    previousContent: FilterDialogResult?,
    onBackClick:()->Unit
) {

    val foo = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(foo)

    val state = rememberLazyListState()

    val listState = rememberLazyListState()

    val showSheet: MutableState<Boolean?> = remember {
        mutableStateOf(false)
    }

    val filterResult = remember {
        mutableStateOf(false)
    }

    if (showSheet.value == true) {
        FilterDialog(
            onDismiss = {
                showSheet.value = false
            },
            onSort = {
                logcat("foobar") { it.toString() }
                onFilterChanged(it)
                filterResult.value = it.isFulfilled()
                showSheet.value = false
            },
            previousContent = previousContent,
            onClear = {
                onFilterChanged(FilterDialogResult())
                filterResult.value = false
                showSheet.value = false
            }
        )
    }
    Column(
        modifier = Modifier.background(YacsaTheme.colors.background)
    ) {
        SearchToolbar(
            state = listState,
            onBackClick = {
                onBackClick()
            },
            onFilterClick = {

            },
            filterState =  filterResult,
            showSheet= showSheet
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
                .clearFocusOnKeyboardDismiss(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = YacsaTheme.colors.accent,
                unfocusedBorderColor = YacsaTheme.colors.primary,
                textColor = YacsaTheme.colors.primary,
                cursorColor = YacsaTheme.colors.accent,
                placeholderColor = YacsaTheme.colors.secondary
            ),
            value = searchText,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onValueChange("")
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = YacsaTheme.colors.accent
                    )
                }
            },
            singleLine = true,
            placeholder = { Text("Type to search...") },
        )

        if (!uiState.topSearch.isNullOrEmpty()) {
            // TODO: fix
            ChipGroup(
                values = uiState.topSearch.map { it.query ?: "" },
                defaultColor = YacsaTheme.colors.background,
                selectedColor = YacsaTheme.colors.surface,
                onSelectedChanged = {
                    onValueChange(it)
                },
                onDelete = {
                    onDelete()
                },
            )
        }

        if (uiState.isResultLoading) {
            ResultIsLoading()
        } else {
            if (uiState.resultSearch.isNullOrEmpty()) {
                if (uiState.isError) {
                    ContentError(errorMessage = "Moshi moshi?")
                } else {
                    ContentNoData(modifier = Modifier.fillMaxSize(),message = "Nothing to show(")
                }
            } else {
                ResultFetched(
                    resultSearch = uiState.resultSearch,
                    onBookClicked = onBookClicked,
                    state = listState
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched() {
    YacsaTheme() {
        ContentFetched(
            searchText = "Lorem ipsum",
            onValueChange = {},
            uiState = SearchUiState(),
            onBookClicked = {},
            onDelete = {},
            onFilterChanged = {},
            previousContent = null,
            onBackClick={}
        )
    }
}

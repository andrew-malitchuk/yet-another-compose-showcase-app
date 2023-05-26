package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.search.screen.composable.ChipGroup
import dev.yacsa.search.screen.search.SearchUiState
import dev.yacsa.search.screen.search.content.result.ResultEmpty
import dev.yacsa.search.screen.search.content.result.ResultError
import dev.yacsa.search.screen.search.content.result.ResultFetched
import dev.yacsa.search.screen.search.content.result.ResultIsLoading
import dev.yacsa.ui.composable.keyboard.clearFocusOnKeyboardDismiss
import dev.yacsa.ui.composable.keyboard.keyboardAsState
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ContentFetched(
    modifier: Modifier = Modifier,
    searchText: String,
    onValueChange: (String) -> Unit,
    uiState: SearchUiState,
    onBookClicked: (Int) -> Unit,
    onDelete: () -> Unit,
) {
    val isShown by keyboardAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clearFocusOnKeyboardDismiss(),
            value = searchText,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = {
                    onValueChange("")
                }) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                }
            },
            singleLine = true,
            placeholder = { Text("Type to search...") },
        )

        if (!uiState.topSearch.isNullOrEmpty()) {
            // TODO: fix
            ChipGroup(
                values = uiState.topSearch.map { it.query ?: "" },
                defaultColor = YacsaTheme.colors.primaryText,
                selectedColor = YacsaTheme.colors.statusBarColor,
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
                    ResultError()
                } else {
                    ResultEmpty()
                }
            } else {
                ResultFetched(
                    resultSearch = uiState.resultSearch,
                    onBookClicked = onBookClicked,
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
        )
    }
}

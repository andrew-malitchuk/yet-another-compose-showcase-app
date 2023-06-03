package dev.yacsa.search.screen.search.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.yacsa.search.screen.composable.ChipGroup
import dev.yacsa.search.screen.search.SearchUiState
import dev.yacsa.search.screen.search.content.result.ResultEmpty
import dev.yacsa.search.screen.search.content.result.ResultError
import dev.yacsa.search.screen.search.content.result.ResultFetched
import dev.yacsa.search.screen.search.content.result.ResultIsLoading
import dev.yacsa.search.screen.search.dialog.FilterDialog
import dev.yacsa.search.screen.search.dialog.FilterDialogResult
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.button.TwoStateButton
import dev.yacsa.ui.composable.divider.AnimatedDivider
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
    previousContent: FilterDialogResult?
) {

    val foo = rememberTopAppBarState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(foo)

    val state = rememberLazyListState()

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

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // TODO: fix
                        Text(text = "Search", style = YacsaTheme.typography.heading)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painterResource(id = R.drawable.icon_search_bold_24),
                            contentDescription = null,
                        )
                    }
                },
                navigationIcon = {
                    SmallFloatingActionButton(onClick = { /*onBackClick()*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                            contentDescription = null,
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = YacsaTheme.colors.primaryBackground,
                ),
            )
            Column {
                Spacer(modifier = Modifier.height(64.dp))
                AnimatedDivider(state = state)
            }
        },
    ) { innerPadding ->
        val corner = 16.dp - (16.dp * Math.abs(foo.collapsedFraction))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                    .background(Color(0xFFE0DFFD)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(),
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
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
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = null
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        onValueChange("")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = null
                                        )
                                    }
                                },
                                singleLine = true,
                                placeholder = { Text("Type to search...") },
                            )
                            BadgedBox(
                                modifier = Modifier.padding(
                                    start = 4.dp,
                                    end = 12.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ),
                                badge = {
                                    if(filterResult.value){
                                    Badge( )
                                    }
                                }
                            ) {
                                TwoStateButton(
                                    modifier = Modifier,
                                    checkedState = showSheet,
                                    defaultIcon = R.drawable.icon_filter_regulat_24,
                                    selectedIcon = R.drawable.icon_filter_bold_24
                                ) {
//                                showSheet.value=it
                                }
                            }
//                            Box() {
//                                TwoStateButton(
//                                    modifier = Modifier.padding(
//                                        start = 4.dp,
//                                        end = 8.dp,
//                                        top = 8.dp,
//                                        bottom = 8.dp
//                                    ),
//                                    checkedState = showSheet,
//                                    defaultIcon = R.drawable.icon_filter_regulat_24,
//                                    selectedIcon = R.drawable.icon_filter_bold_24
//                                ) {
////                                showSheet.value=it
//                                }
//                            }
                        }
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
                    }

//                    if (checked.value==true) {
//                        ExpandableCard(
//                            "Filter",
//                            YacsaTheme.colors.primaryText
//                        ) {
//                            ContentFilter()
//                        }
//                    }

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
//                    if (showSheet.value==true) {
//                        FilterDialog() {
//                            showSheet.value = false
//                        }
//                    }
                }
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
            previousContent=null
        )
    }
}

package dev.yacsa.deeplink.screen.deeplink.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.yacsa.deeplink.screen.deeplink.DeeplinkUiState
import dev.yacsa.deeplink.screen.deeplink.item.DeeplinkItem
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.content.ContentError
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.keyboard.clearFocusOnKeyboardDismiss
import dev.yacsa.ui.theme.YacsaTheme
import java.lang.Math.abs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSnapperApi::class)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPadding: PaddingValues,
    state: LazyListState,
    foo: TopAppBarState,
    uiState: DeeplinkUiState,
    foobar: MutableState<String>,
    onDeeplinkRun:()->Unit

) {
    val corner =
        YacsaTheme.corners.medium - (YacsaTheme.corners.medium * abs(foo.collapsedFraction))
    val systemUiController = rememberSystemUiController()

    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.navigationBar,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.statusBar,
            )
            ContentError(
                errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString()
            ) {

            }
        }

        else -> {
            systemUiController.apply {
                setSystemBarsColor(
                    color = YacsaTheme.colors.statusBar,
                )
                setNavigationBarColor(
                    color = YacsaTheme.colors.surface,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(YacsaTheme.colors.background)
                        .padding(
                            start = YacsaTheme.spacing.small,
                            end = YacsaTheme.spacing.extraSmall,
                            top = YacsaTheme.spacing.small,
                            bottom = YacsaTheme.spacing.small
                        )
                        .clearFocusOnKeyboardDismiss(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = YacsaTheme.colors.accent,
                        unfocusedBorderColor = YacsaTheme.colors.primary,
                        textColor = YacsaTheme.colors.primary,
                        cursorColor = YacsaTheme.colors.accent,
                        placeholderColor = YacsaTheme.colors.secondary
                    ),
                    value = foobar.value,
                    onValueChange = {
                        foobar.value = it
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    leadingIcon = {
                        androidx.compose.material3.Icon(
                            painter = painterResource(id = R.drawable.icon_link_bold_24),
                            contentDescription = null,
                            tint = YacsaTheme.colors.accent,
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            onDeeplinkRun()
                        }) {
                            androidx.compose.material3.Icon(
                                painter = painterResource(id = R.drawable.icon_run_bold_24),
                                contentDescription = null,
                                tint = YacsaTheme.colors.accent,
                            )
                        }
                    },
                    singleLine = true,
                    placeholder = { Text(UiText.StringResource(dev.yacsa.localization.R.string.deeplink_type).asString()) },
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f)
                        .background(YacsaTheme.colors.background)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                            .background(YacsaTheme.colors.surface)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            state = state,
                            contentPadding = PaddingValues(YacsaTheme.spacing.small),
                            verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
                        ) {
                            item {
                                DeeplinkItem(
                                    value = "yacsa://book",
                                    icon = R.drawable.icon_link_regular_24,
                                    onClick = {
                                        foobar.value="yacsa://book"
                                    },
                                )
                            }
                            item {
                                DeeplinkItem(
                                    value = "yacsa://favourite",
                                    icon = R.drawable.icon_link_regular_24,
                                    onClick = {
                                        foobar.value="yacsa://favourite"
                                    },
                                )
                            }
                            item {
                                DeeplinkItem(
                                    value = "yacsa://search",
                                    icon = R.drawable.icon_link_regular_24,
                                    onClick = {
                                        foobar.value="yacsa://search"
                                    },
                                )
                            }

                        }
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview_ContentFetched() {
    YacsaTheme {
        ContentFetched(
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            state = rememberLazyListState(),
            foo = rememberTopAppBarState(),
            uiState = DeeplinkUiState(isError = true),
            foobar = remember { mutableStateOf("") },
            onDeeplinkRun={}
        )
    }
}

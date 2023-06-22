package dev.yacsa.settings.screen.settings.item

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.modifier.bouncingClickable
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun LanguageItem(
    modifier: Modifier = Modifier,
    language: MutableState<String?>,
) {
    val shape = YacsaTheme.shapes.cornersStyle

    var isUk: MutableState<Boolean?> =
        remember {
            mutableStateOf(null)
        }
    var isEn: MutableState<Boolean?> =
        remember {
            mutableStateOf(null)
        }

    isUk.value = language.value == "uk"
    isEn.value = language.value == "en" || language.value == null

    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .bouncingClickable {
            },
        backgroundColor = YacsaTheme.colors.surface,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(YacsaTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(
                onClick = { },
                containerColor = YacsaTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_translate_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent,
                )
            }
            Spacer(
                modifier = Modifier
                    .width(YacsaTheme.spacing.medium),
            )
            Text(
                text = UiText.StringResource(dev.yacsa.localization.R.string.settings_lang).asString(),
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary,
            )
            Spacer(
                modifier = Modifier
                    .weight(1f),
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
                contentPadding = PaddingValues(horizontal = 1.dp),
            ) {
                item {
                    IconToggleButton(
                        checked = isEn.value ?: false,
                        onCheckedChange = {
                            if (it) {
                                isUk.value = !it
                                isEn.value = it
                                language.value = "en"
                            }
                        },
                    ) {
                        Crossfade(
                            targetState = isEn.value,
                        ) { isChecked ->
                            if (isChecked == true) {
                                Text(
                                    text = "En",
                                    style = YacsaTheme.typography.title,
                                    color = YacsaTheme.colors.accent,
                                    fontWeight = FontWeight.Bold,
                                )
                            } else {
                                Text(
                                    text = "En",
                                    style = YacsaTheme.typography.title,
                                    color = YacsaTheme.colors.accent,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
                item {
                    IconToggleButton(
                        checked = isUk.value ?: false,
                        onCheckedChange = {
                            if (it) {
                                isUk.value = it
                                isEn.value = !it
                                language.value = "uk"
                            }
                        },
                    ) {
                        Crossfade(
                            targetState = isUk.value,
                        ) { isChecked ->
                            if (isChecked == true) {
                                Text(
                                    text = "Uk",
                                    style = YacsaTheme.typography.title,
                                    color = YacsaTheme.colors.accent,
                                    fontWeight = FontWeight.Bold,
                                )
                            } else {
                                Text(
                                    text = "Uk",
                                    style = YacsaTheme.typography.title,
                                    color = YacsaTheme.colors.accent,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LanguageItem_Light() {
    YacsaTheme(false) {
        LanguageItem(
            language = remember { mutableStateOf("uk") },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LanguageItem_Dark() {
    YacsaTheme(true) {
        LanguageItem(
            language = remember { mutableStateOf("uk") },
        )
    }
}

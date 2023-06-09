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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.model.model.theme.isAuto
import dev.yacsa.model.model.theme.isDark
import dev.yacsa.model.model.theme.isLight
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.modifier.bouncingClickable
import dev.yacsa.ui.theme.YacsaTheme

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    theme: MutableState<ThemeUiModel?>,
) {
    val shape = YacsaTheme.shapes.cornersStyle


    var isLight: MutableState<Boolean?> =
        remember {
            mutableStateOf(null)
        }
    var isDark: MutableState<Boolean?> =
        remember {
            mutableStateOf(null)
        }
    var isAuto: MutableState<Boolean?> =
        remember {
            mutableStateOf(null)
        }

    isLight.value = theme.value?.isLight()
    isDark.value = theme.value?.isDark()
    isAuto.value = theme.value?.isAuto()

//    val theme: MutableState<ThemeUiModel?> =
//        remember {
//            mutableStateOf(null)
//        }

    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .bouncingClickable {
            },
        backgroundColor = YacsaTheme.colors.surface,
        /*border = BorderStroke(0.5.dp, YacsaTheme.colors.primary),*/
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SmallFloatingActionButton(
                onClick = { },
                containerColor = YacsaTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_paint_roller_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp),
            )
            Text(
                text = "Theme",
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.secondary
            )
            Spacer(
                modifier = Modifier
                    .weight(1f),
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 1.dp)
            ) {
                item {

                    IconToggleButton(
                        checked = isLight.value ?: false,
                        onCheckedChange = {
                            if(it){
                                isDark.value = !it
                                isLight.value = it
                                isAuto.value = !it
                                theme.value = ThemeUiModel.LIGHT
                            }
                        }) {

                        Crossfade(
                            targetState = isLight.value
                        ) { isChecked ->
                            if (isChecked == true) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_sun_bold_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_sun_regular_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
                                )
                            }
                        }
                    }
                }
                item {
                    IconToggleButton(
                        checked = isDark.value ?: false,
                        onCheckedChange = {

                            if(it){
                                isDark.value = it
                                isLight.value = !it
                                isAuto.value = !it
                                theme.value = ThemeUiModel.DARK
                            }
                        }) {

                        Crossfade(
                            targetState = isDark.value
                        ) { isChecked ->
                            if (isChecked == true) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_moon_bold_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_moon_regular_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
                                )
                            }
                        }
                    }
                }
                item {
                    IconToggleButton(checked = isAuto.value ?: false, onCheckedChange = {

                        if(it){
                            isDark.value = !it
                            isLight.value = !it
                            isAuto.value = it
                            theme.value = ThemeUiModel.AUTO
                        }
                    }) {
                        Crossfade(
                            targetState = isAuto.value
                        ) { isChecked ->
                            if (isChecked == true) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_android_logo_bold_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_android_logo_regular_24),
                                    contentDescription = null,
                                    tint = YacsaTheme.colors.accent
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
fun Preview_ThemeItem() {
    YacsaTheme {
        ThemeItem(
            theme = remember { mutableStateOf(null) },
        )
    }
}

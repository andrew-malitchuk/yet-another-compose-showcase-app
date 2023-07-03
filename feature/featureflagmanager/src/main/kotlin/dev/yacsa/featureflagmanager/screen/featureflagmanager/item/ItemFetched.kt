package dev.yacsa.featureflagmanager.screen.featureflagmanager.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemFetched(
    modifier: Modifier = Modifier,
    item: FeatureFlagModel,
    isEnabled: (Boolean) -> Unit,
    isActive: (Boolean) -> Unit,
) {
    val checkboxState = remember { mutableStateOf(item.value != null) }
    val switchState = remember { mutableStateOf(item.value) }

    val shape = RoundedCornerShape(YacsaTheme.corners.medium)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        contentColor = YacsaTheme.colors.background,
        backgroundColor = YacsaTheme.colors.background,
        shape = YacsaTheme.shapes.cornersStyle,
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
                    painter = painterResource(id = dev.yacsa.ui.R.drawable.icon_command_regular_24),
                    contentDescription = null,
                    tint = YacsaTheme.colors.accent,
                )
            }
            Spacer(
                modifier = Modifier
                    .width(YacsaTheme.spacing.medium),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.basicMarquee(),
                    text = item.key,
                    style = YacsaTheme.typography.title,
                    color = YacsaTheme.colors.primary,
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = YacsaTheme.colors.accent,
                        checkedTrackColor = YacsaTheme.colors.primary,

                        uncheckedThumbColor = YacsaTheme.colors.surface,
                        uncheckedTrackColor = YacsaTheme.colors.secondary,
                    ),
                    checked = switchState.value ?: false,
                    onCheckedChange = {
                        switchState.value = it
                        isActive(it)
                    },
                    enabled = checkboxState.value,
                )
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = YacsaTheme.colors.accent,
                    ),
                    checked = checkboxState.value,
                    onCheckedChange = {
                        checkboxState.value = it
                        isEnabled(it)
                    },
                    modifier = Modifier
                        .padding(YacsaTheme.spacing.medium),
                )
            }
        }
    }

//    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Text(
//                modifier = Modifier
//                    .padding( YacsaTheme.spacing.small, ),
//                text = item.key,
//            )
//            Spacer(modifier = Modifier.weight(1f))
//            Switch(
//                checked = switchState.value ?: false,
//                onCheckedChange = {
//                    switchState.value = it
//                    isActive(it)
//                },
//                enabled = checkboxState.value,
//            )
//            Checkbox(
//                checked = checkboxState.value,
//                onCheckedChange = {
//                    checkboxState.value = it
//                    isEnabled(it)
//                },
//                modifier = Modifier
//                    .padding( YacsaTheme.spacing.medium, ),
//            )
//        }
//        Divider(
//            modifier = Modifier
//                .fillMaxWidth(),
//        )
//    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ItemFetched_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        ItemFetched(
            item = FeatureFlagModel(faker.quote.fortuneCookie(), true),
            isActive = {},
            isEnabled = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Preview_ItemFetched_Dark() {
    val faker = Faker()
    YacsaTheme(true) {
        ItemFetched(
            item = FeatureFlagModel(faker.quote.fortuneCookie(), true),
            isActive = {},
            isEnabled = {},
        )
    }
}

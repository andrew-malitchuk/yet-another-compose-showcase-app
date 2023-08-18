package dev.yacsa.widget.favourite

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.Text
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

class FavouriteBooksAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val pref = currentState<Preferences>()
            val foo = pref[stringPreferencesKey("foo")]
            val list = foo?.let { Foo().fromJson(it) }
            Content(list)
        }
    }
}

@Composable
fun Content(list: List<BookUiModel>?) {
    androidx.glance.layout.Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xFFF7EFE7))
            .cornerRadius(8.dp)
    ) {
        if (list.isNullOrEmpty()) {
            Text(text = "NI")
        } else {
            LazyColumn(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(list) { book ->
                    book?.let {
                        Column(modifier = GlanceModifier.fillMaxSize()) {
                            Spacer(modifier = GlanceModifier.height(4.dp))
                            Box(
                                modifier = GlanceModifier
                                    .fillMaxWidth()
                                    .clickable {
                                    }
                                    .background(Color(0xFFFFFFFF))
                                    .cornerRadius(8.dp)
                            ) {
                                Row(modifier = GlanceModifier.fillMaxSize()) {
                                    Box() {
                                        Box() {

                                        }
                                        Box(modifier = GlanceModifier.padding(1.dp)) {
//                                            Image(provider = , contentDescription = )
                                        }
                                    }
                                }

                            }
                            Spacer(modifier = GlanceModifier.height(4.dp))
                        }

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview_Content_Light() {
    YacsaTheme(false) {

    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Content_Dark() {
    YacsaTheme(true) {

    }
}

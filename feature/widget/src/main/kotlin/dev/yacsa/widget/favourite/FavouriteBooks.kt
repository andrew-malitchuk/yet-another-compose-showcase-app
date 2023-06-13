package dev.yacsa.widget.favourite

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.text.Text
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.ui.theme.YacsaTheme

class FavouriteBooksAppWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val pref = currentState<Preferences>()
            val foo = pref[stringPreferencesKey("foo")]
            val list = foo?.let { Foo().fromJson(it) }
            YacsaTheme() {
                Content(list)
            }
        }
    }

}

@Composable
private fun Content(foo:List<BookUiModel>?){
    Box() {
        if (foo.isNullOrEmpty()) {
            Text(text = "NI")
        } else {
            Row() {
                foo.forEach {
                    Text(text = it.title.toString())
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Content_Light(){
    YacsaTheme(false) {

    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Content_Dark(){
    YacsaTheme(true) {

    }
}

package dev.yacsa.widget.favourite

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import dagger.hilt.android.AndroidEntryPoint
import dev.yacsa.domain.usecase.books.NewSearchBooksUseCase
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteBooksAppWidgetReceiver : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var newSearchBooksUseCase: NewSearchBooksUseCase

    @Inject
    lateinit var booksUiDomainModelMapper:NewBooksUiDomainMapper

    private val coroutineScope = MainScope()

    override val glanceAppWidget: GlanceAppWidget = FavouriteBooksAppWidget()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        observeData(context)
    }

    private fun observeData(context: Context) {
        coroutineScope.launch {

            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(FavouriteBooksAppWidget::class.java)
                    .firstOrNull()

            val result = newSearchBooksUseCase("foo").fold(
                {
                    null
                }, {list->
                    val books = list.map{
                        booksUiDomainModelMapper.toUi(it)
                    }
                    books
                }
            )

            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { preferences ->
                    preferences.toMutablePreferences().apply {
                        result?.let{list->
                            this[foo] =   Foo().getJson(list)
                        }
                    }
                }
                glanceAppWidget.update(context, it)
            }
        }
    }

    companion object {
        val foo = stringPreferencesKey("foo")
    }

}
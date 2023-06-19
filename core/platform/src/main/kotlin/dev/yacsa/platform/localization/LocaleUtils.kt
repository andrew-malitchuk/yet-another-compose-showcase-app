package dev.yacsa.platform.localization

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleUtils {

    // [AppPrefs] is sharedpreferences or datastore
    fun setLocale(context: Context, language: String?) = updateResources(context, language ) //use locale codes

    private fun updateResources(context: Context, language: String?) {
        context.resources.apply {
            val locale = Locale(language)
            val config = Configuration(configuration)

            context.createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            context.resources.updateConfiguration(config, displayMetrics)
        }
    }
}


package dev.yacsa.provider.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yacsa.analytics.event.ContentViewAnalyticModel
import dev.yacsa.analytics.event.CustomAnalyticModel
import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.analytics.event.base.EventAnalyticModel
import dev.yacsa.domain.model.analytics.AnalyticsDomainModel
import dev.yacsa.domain.usecase.analytics.AddAnalyticUseCase
import dev.yacsa.provider.AnalyticProvider
import dev.yacsa.provider.notification.collection.FiniteList
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

class NotificationAnalyticProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val addAnalyticUseCase: AddAnalyticUseCase
) : AnalyticProvider {

    val notificationContent = FiniteList<String>(5)

    init {
        createNotificationChannel()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun log(event: EventAnalyticModel) {
        when (event) {
            is ContentViewAnalyticModel -> {
                showNotification(
                    "YACSA Analytics",
                    getContentViewAnalytic(event)
                )
                // TODO: fix?
                addAnalyticUseCase(
                    AnalyticsDomainModel(
                        "content_view",
                        event.viewName
                    )
                )
            }

            is CustomAnalyticModel -> {
                showNotification(
                    "YACSA Analytics",
                    getCustomEventAnalytic(event)
                )
                addAnalyticUseCase(
                    AnalyticsDomainModel(
                        event.eventName,
                        getCustomAnalyticsParams(event)
                    )
                )
            }
        }
    }

    override suspend fun setProperty(property: UserPropertyAnalyticModel) {
        showNotification(
            "YACSA Analytics",
            "\uD83E\uDD14 ${getAnalyticProperty(property)}"
        )
        addAnalyticUseCase(
            AnalyticsDomainModel(
                "user_property",
                getAnalyticProperty(property)
            )
        )
    }


    private fun getAnalyticProperty(property: UserPropertyAnalyticModel): String {
        return "${property.key} : ${property.value}"
    }

    private fun getContentViewAnalytic(event: ContentViewAnalyticModel): String {
        return "\uD83D\uDCF1 ${event.viewName}"
    }


    private fun getCustomEventAnalytic(event: CustomAnalyticModel): String {
        return "\uD83D\uDD14 ${event.eventName} : {${getCustomAnalyticsParams(event)}}"
    }

    fun getCustomAnalyticsParams(event: CustomAnalyticModel): String {
        var params: String? = null

        if (event.getParameters().isNotEmpty()) {
            params = ""
        }
        event.getParameters().forEach {
            params += "${it.key} : ${it.value}, "
        }
        return "{${params}}"
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, content: String) {

        notificationContent.add(content)

        val notification = NotificationCompat.Builder(context, "dev.yacsa")
            .setContentTitle(title)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(notificationContent.toString())
            )
            .setSmallIcon(dev.yacsa.ui.R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1010, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "dev.yacsa",
                "Analytics",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}
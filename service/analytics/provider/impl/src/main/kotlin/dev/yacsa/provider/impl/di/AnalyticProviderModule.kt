package dev.yacsa.provider.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.provider.AnalyticProvider
import dev.yacsa.provider.firebase.FirebaseAnalyticProvider
import dev.yacsa.provider.local.LocalAnalyticProvider
import dev.yacsa.provider.notification.NotificationAnalyticProvider

@Module
@InstallIn(SingletonComponent::class)
class AnalyticProviderModule {

    @Provides
    fun providesProviders(
        localAnalyticProvider: LocalAnalyticProvider,
        notificationAnalyticProvider: NotificationAnalyticProvider,
        firebaseAnalyticProvider: FirebaseAnalyticProvider,
    ): ArrayList<AnalyticProvider> {
        return arrayListOf(
            localAnalyticProvider,
            notificationAnalyticProvider,
            firebaseAnalyticProvider,
        )
    }
}

package dev.yacsa.platform.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yacsa.platform.connection.ConnectivityObserver
import dev.yacsa.platform.connection.impl.NetworkConnectivityObserver

@Module
@InstallIn(SingletonComponent::class)
class ConnectivityModule {

    @Provides
    fun providesConnectivityObserver(
        @ApplicationContext context: Context,
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(
            context,
        )
    }
}

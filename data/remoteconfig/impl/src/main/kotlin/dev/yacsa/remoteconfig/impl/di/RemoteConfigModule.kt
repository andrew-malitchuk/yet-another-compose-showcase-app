package dev.yacsa.remoteconfig.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.remoteconfig.impl.config.RemoteConfigure

@Module
@InstallIn(SingletonComponent::class)
class RemoteConfigModule {

    @Provides
    fun providesRemoteConfigure(): RemoteConfigure {
        return RemoteConfigure()
    }

}
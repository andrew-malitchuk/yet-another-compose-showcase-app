package dev.yacsa.remoteconfig.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.remoteconfig.impl.source.RemoteConfigSourceImpl
import dev.yacsa.remoteconfig.source.RemoteConfigSource

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteConfigSourceModule {

    @Binds
    abstract fun bindsRemoteConfigSource(
        remoteConfigSourceImpl: RemoteConfigSourceImpl,
    ): RemoteConfigSource

}

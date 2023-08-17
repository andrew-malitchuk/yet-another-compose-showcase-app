package dev.yacsa.cryptodatastore.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.cryptodatastore.impl.source.AccessTokenCryptoDataStoreSourceImpl
import dev.yacsa.cryptodatastore.source.AccessTokenCryptoDataStoreSource

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoDataStoreSourceModule {

    @Binds
    abstract fun bindsAccessTokenCryptoDataStoreSource(
        accessTokenCryptoDataStoreSourceImpl: AccessTokenCryptoDataStoreSourceImpl,
    ): AccessTokenCryptoDataStoreSource
}

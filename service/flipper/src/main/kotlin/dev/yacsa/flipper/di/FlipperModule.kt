package dev.yacsa.flipper.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FlipperModule {

    @Provides
    @Singleton
    fun providesNetworkFlipperPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }

    @Provides
    @Named("flipper_okhttp_interceptor")
    fun providesFlipperOkHttpInterceptor(
        networkFlipperPlugin: NetworkFlipperPlugin,
    ): Interceptor {
        return FlipperOkhttpInterceptor(networkFlipperPlugin, true)
    }
}

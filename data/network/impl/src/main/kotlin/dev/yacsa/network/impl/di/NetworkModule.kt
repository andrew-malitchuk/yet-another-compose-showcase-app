package dev.yacsa.network.impl.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import logcat.LogPriority
import logcat.logcat
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesOkHttpClient(
        @Named("interceptor_chucker") chuckerInterceptor: Interceptor,
        @Named("interceptor_logging") loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            addInterceptor(chuckerInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    fun providesConverterFactory(
        moshi: Moshi
    ): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Named("interceptor_chucker")
    fun providesInterceptorChucker(
        @ApplicationContext applicationContext: Context
    ): Interceptor {
        return ChuckerInterceptor.Builder(applicationContext).apply {
            collector(
                ChuckerCollector(
                    applicationContext,
                    true,
                    RetentionManager.Period.FOREVER
                )
            )
        }.build()
    }

    @Provides
    @Named("interceptor_logging")
    fun providesInterceptorLogging(
    ): Interceptor {
        return HttpLoggingInterceptor {
            logcat(tag = "network", priority = LogPriority.DEBUG) { it }
        }.setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    @Named("base_url")
    fun providesBaseUrl(): String {
        return "https://gutendex.com"
    }

    companion object {
        private const val CONNECTION_TIMEOUT = 10_000L
        private const val READ_TIMEOUT = 10_000L
        private const val WRITE_TIMEOUT = 10_000L
    }
}
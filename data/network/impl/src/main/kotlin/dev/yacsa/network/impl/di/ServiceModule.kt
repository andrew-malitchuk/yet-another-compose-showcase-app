package dev.yacsa.network.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.network.impl.service.BooksApiService
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun providesBooksApiService(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient,
        converter: Converter.Factory,
    ): BooksApiService {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addConverterFactory(converter)
        }.build().create(BooksApiService::class.java)
    }
}

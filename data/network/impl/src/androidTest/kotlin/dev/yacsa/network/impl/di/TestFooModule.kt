package dev.yacsa.network.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class TestFooModule {
    @Provides
    fun providesFoo():String{
        return "foo"
    }
}
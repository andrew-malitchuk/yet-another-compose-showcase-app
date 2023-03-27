package dev.yacsa.repository.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.repository.impl.mapper.StartUpConfigureRepoDataStoreMapper


@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
class RepositoryMapperModule {

    @Provides
    fun providesGreetingMapper(): StartUpConfigureRepoDataStoreMapper {
        return StartUpConfigureRepoDataStoreMapper()
    }

}
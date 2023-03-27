package dev.yacsa.domain.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.mapper.StartUpConfigureDomainRepoMapper

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
class DomainMapperModule {

    @Provides
    fun providesStartUpConfigureDomainRepoMapper(): StartUpConfigureDomainRepoMapper {
        return StartUpConfigureDomainRepoMapper()
    }

}
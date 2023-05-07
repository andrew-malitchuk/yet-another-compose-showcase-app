package dev.yacsa.domain.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapperImpl
import dev.yacsa.domain.impl.mapper.NewFormatsDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewFormatsDomainRepoMapperImpl
import dev.yacsa.domain.impl.mapper.NewPersonDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewPersonDomainRepoMapperImpl
import dev.yacsa.domain.impl.mapper.NewRemoteKeyDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewRemoteKeyDomainRepoMapperImpl
import dev.yacsa.domain.impl.mapper.NewSearchHistoryDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewSearchHistoryDomainRepoMapperImpl
import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapper
import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapperImpl

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
class DomainMapperModule {

    @Provides
    fun providesNewSearchHistoryDomainRepoMapper(): NewSearchHistoryDomainRepoMapper {
        return NewSearchHistoryDomainRepoMapperImpl()
    }

    @Provides
    fun providesNewPersonDomainRepoMapper(): NewPersonDomainRepoMapper {
        return NewPersonDomainRepoMapperImpl()
    }

    @Provides
    fun providesNewRemoteKeyDomainRepoMapper(): NewRemoteKeyDomainRepoMapper {
        return NewRemoteKeyDomainRepoMapperImpl()
    }

    @Provides
    fun providesNewStartUpConfigureDomainRepoMapper(): NewStartUpConfigureDomainRepoMapper {
        return NewStartUpConfigureDomainRepoMapperImpl()
    }

    @Provides
    fun providesNewFormatsDomainRepoMapper(): NewFormatsDomainRepoMapper {
        return NewFormatsDomainRepoMapperImpl()
    }

    @Provides
    fun providesNewBookDomainRepoMapper(): NewBookDomainRepoMapper {
        return NewBookDomainRepoMapperImpl()
    }

}

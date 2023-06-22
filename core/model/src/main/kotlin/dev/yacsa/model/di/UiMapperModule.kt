package dev.yacsa.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.model.mapper.AnalyticsUiDomainMapper
import dev.yacsa.model.mapper.AnalyticsUiDomainMapperImpl
import dev.yacsa.model.mapper.FeatureFlagDomainRepoMapper
import dev.yacsa.model.mapper.FeatureFlagDomainRepoMapperImpl
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.model.mapper.NewBooksUiDomainMapperImpl
import dev.yacsa.model.mapper.NewFormatsUiDomainMapper
import dev.yacsa.model.mapper.NewFormatsUiDomainMapperImpl
import dev.yacsa.model.mapper.NewPersonUiDomainMapper
import dev.yacsa.model.mapper.NewPersonUiDomainMapperImpl
import dev.yacsa.model.mapper.NewSearchHistoryUiDomainMapper
import dev.yacsa.model.mapper.NewSearchHistoryUiDomainMapperImpl

@Module
@InstallIn(SingletonComponent::class)
class UiMapperModule {

    @Provides
    fun providesNewFormatsUiDomainMapper(): NewFormatsUiDomainMapper {
        return NewFormatsUiDomainMapperImpl()
    }

    @Provides
    fun providesNewBooksUiDomainMapper(): NewBooksUiDomainMapper {
        return NewBooksUiDomainMapperImpl()
    }

    @Provides
    fun providesNewSearchHistoryUiDomainMapper(): NewSearchHistoryUiDomainMapper {
        return NewSearchHistoryUiDomainMapperImpl()
    }

    @Provides
    fun providesNewPersonUiDomainMapper(): NewPersonUiDomainMapper {
        return NewPersonUiDomainMapperImpl()
    }

    @Provides
    fun providesAnalyticsUiDomainMapper(): AnalyticsUiDomainMapper {
        return AnalyticsUiDomainMapperImpl()
    }

    @Provides
    fun providesFeatureFlagDomainRepoMapper(): FeatureFlagDomainRepoMapper {
        return FeatureFlagDomainRepoMapperImpl()
    }
}

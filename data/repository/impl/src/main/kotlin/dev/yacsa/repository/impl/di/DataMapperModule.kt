package dev.yacsa.repository.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.repository.impl.mapper.book.NewBookRepoDbMapper
import dev.yacsa.repository.impl.mapper.book.NewBookRepoDbMapperImpl
import dev.yacsa.repository.impl.mapper.book.NewBookRepoNetMapper
import dev.yacsa.repository.impl.mapper.book.NewBookRepoNetMapperImpl
import dev.yacsa.repository.impl.mapper.featureflag.NewFeatureFlagRepoDbMapper
import dev.yacsa.repository.impl.mapper.featureflag.NewFeatureFlagRepoDbMapperImpl
import dev.yacsa.repository.impl.mapper.formats.NewFormatsRepoDbMapper
import dev.yacsa.repository.impl.mapper.formats.NewFormatsRepoDbMapperImpl
import dev.yacsa.repository.impl.mapper.formats.NewFormatsRepoNetMapper
import dev.yacsa.repository.impl.mapper.formats.NewFormatsRepoNetMapperImpl
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoDbMapper
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoDbMapperImpl
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoNetMapper
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoNetMapperImpl
import dev.yacsa.repository.impl.mapper.search.NewSearchHistoryRepoDbMapper
import dev.yacsa.repository.impl.mapper.search.NewSearchHistoryRepoDbMapperImpl
import dev.yacsa.repository.impl.mapper.startup.NewStartUpConfigureRepoDataStoreMapper
import dev.yacsa.repository.impl.mapper.startup.NewStartUpConfigureRepoDataStoreMapperImpl

@Module
@InstallIn(SingletonComponent::class)
class DataMapperModule {

    @Provides
    fun providesNewBookRepoDbMapper(): NewBookRepoDbMapper {
        return NewBookRepoDbMapperImpl()
    }

    @Provides
    fun providesNewBookRepoNetMapper(): NewBookRepoNetMapper {
        return NewBookRepoNetMapperImpl()
    }

    @Provides
    fun providesNewPersonRepoNetMapper(): NewPersonRepoNetMapper {
        return NewPersonRepoNetMapperImpl()
    }

    @Provides
    fun providesNewPersonRepoDbMapper(): NewPersonRepoDbMapper {
        return NewPersonRepoDbMapperImpl()
    }

    @Provides
    fun providesNewFeatureFlagRepoDbMapper(): NewFeatureFlagRepoDbMapper {
        return NewFeatureFlagRepoDbMapperImpl()
    }

    @Provides
    fun providesNewSearchHistoryRepoDbMapper(): NewSearchHistoryRepoDbMapper {
        return NewSearchHistoryRepoDbMapperImpl()
    }

    @Provides
    fun providesNewFormatsRepoNetMapper(): NewFormatsRepoNetMapper {
        return NewFormatsRepoNetMapperImpl()
    }

    @Provides
    fun providesNewFormatsRepoDbMapper(): NewFormatsRepoDbMapper {
        return NewFormatsRepoDbMapperImpl()
    }

    @Provides
    fun providesNewStartUpConfigureRepoDataStoreMapper(): NewStartUpConfigureRepoDataStoreMapper {
        return NewStartUpConfigureRepoDataStoreMapperImpl()
    }
}

package dev.yacsa.repository.impl

import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dev.yacsa.database.source.AnalyticsDbSource
import dev.yacsa.repository.impl.impl.AnalyticsRepositoryImpl
import dev.yacsa.repository.impl.mapper.analytics.AnalyticsRepoDbMapper
import dev.yacsa.repository.repository.AnalyticsRepository
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class TestRepository {

    private val faker = Faker()

    lateinit var analyticsRepository: AnalyticsRepository

    @Mock
    lateinit var analyticsDbSource: AnalyticsDbSource

    @Mock
    lateinit var analyticsRepoDbMapper: AnalyticsRepoDbMapper

    @Before
    fun setup() {
        analyticsRepository = AnalyticsRepositoryImpl(
            analyticsDbSource,
            analyticsRepoDbMapper,
        )
    }

    @Test
    fun foo() = runTest {
        assertNotNull(analyticsRepository.get())
    }

//    @Test
//    fun `()`()= runTest{
//        //region Arrange
//        //endregion Arrange
//
//        //region Act
//        //endregion Act
//
//        //region Assert
//        //endregion Assert
//    }
}

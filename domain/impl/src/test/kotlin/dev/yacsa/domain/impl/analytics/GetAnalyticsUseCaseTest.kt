package dev.yacsa.domain.impl.analytics

import dev.yacsa.domain.impl.mapper.analytics.AnalyticsDomainRepoMapper
import dev.yacsa.domain.impl.mapper.analytics.AnalyticsDomainRepoMapperImpl
import dev.yacsa.domain.impl.usecase.analytics.GetAnalyticsUseCaseImpl
import dev.yacsa.domain.usecase.analytics.GetAnalyticsUseCase
import dev.yacsa.repository.repository.AnalyticsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class GetAnalyticsUseCaseTest {

    lateinit var useCase: GetAnalyticsUseCase
    var analyticsRepository: AnalyticsRepository = mock()

    lateinit var analyticsDomainRepoMapper: AnalyticsDomainRepoMapper


    @Before
    fun before() {
        analyticsDomainRepoMapper = AnalyticsDomainRepoMapperImpl()
        Mockito.reset(analyticsRepository)
        useCase = GetAnalyticsUseCaseImpl(
            analyticsRepository,
            analyticsDomainRepoMapper
        )
    }

    @Test
    fun `if there is no data usecase returns emptyList`() = runBlocking{

        Mockito.`when`(analyticsRepository.get()).thenReturn(emptyList())

        val actual = useCase()

        assertEquals(emptyList(),actual.getOrNull())
    }

    @Test
    fun `if there is an error usecase return DomainError`(): Unit = runBlocking {
        Mockito.`when`(analyticsRepository.get()).doThrow(NullPointerException())

        val actual = useCase().leftOrNull()
        assertNotNull(actual)
    }

}
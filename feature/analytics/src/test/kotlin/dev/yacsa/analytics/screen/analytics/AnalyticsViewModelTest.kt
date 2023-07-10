package dev.yacsa.analytics.screen.analytics

import androidx.lifecycle.SavedStateHandle
import dev.yacsa.domain.usecase.analytics.ClearAnalyticsUseCase
import dev.yacsa.domain.usecase.analytics.GetAnalyticsUseCase
import dev.yacsa.model.mapper.AnalyticsUiDomainMapper
import dev.yacsa.model.mapper.AnalyticsUiDomainMapperImpl
import dev.yacsa.platform.Theme
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.Mockito
import org.mockito.kotlin.mock
import kotlin.test.assertNotNull

class AnalyticsViewModelTest {


    @JvmField
    @RegisterExtension
    val mainDispatcherExtension = MainDispatcherExtension()

    @SpyK
    private var savedStateHandle = SavedStateHandle()

    private val getAnalyticsUseCase: GetAnalyticsUseCase = mock()
    private val clearAnalyticsUseCase: ClearAnalyticsUseCase = mock()
    private val analyticsUiDomainMapper: AnalyticsUiDomainMapper = AnalyticsUiDomainMapperImpl()

    var theme: Theme = mock()

    lateinit var viewModel: AnalyticsViewModel

    @Before
    fun init() {

    }




    @AfterEach
    fun afterEach() {
        Mockito.reset(savedStateHandle)
        Mockito.reset(getAnalyticsUseCase)
        Mockito.reset(clearAnalyticsUseCase)
        Mockito.reset(analyticsUiDomainMapper)
        Mockito.reset(theme)
    }

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = AnalyticsViewModel(
            savedStateHandle,
            AnalyticsUiState(),
            getAnalyticsUseCase,
            clearAnalyticsUseCase,
            analyticsUiDomainMapper,
            theme
        )
    }

    @Test
    fun foo()= runTest {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = AnalyticsViewModel(
            savedStateHandle,
            AnalyticsUiState(),
            getAnalyticsUseCase,
            clearAnalyticsUseCase,
            analyticsUiDomainMapper,
            theme
        )

        assertNotNull(viewModel)
//        viewModel.event.test{
//
//        }
    }
}

class MainDispatcherExtension(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
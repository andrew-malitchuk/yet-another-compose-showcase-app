package dev.yacsa.network.impl

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dev.yacsa.network.impl.di.NetworkModule
import dev.yacsa.network.impl.service.BooksApiService
import dev.yacsa.network.impl.utils.loadFileText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.HttpURLConnection
import javax.inject.Inject
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class MainTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private var mockWebServer = MockWebServer()

    private lateinit var apiService: BooksApiService

    @Inject
    lateinit var converter: Converter.Factory

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun init() {
        hiltRule.inject()
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(converter)
            .client(okHttpClient)
            .build()
            .create(BooksApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSomething() = runTest {

        // Assign
        val source = loadFileText(this, "/book_success.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(source)
        mockWebServer.enqueue(response)
        // Act
        val product = apiService.getBook(1)

        assertEquals(source,product.toString())
    }

}
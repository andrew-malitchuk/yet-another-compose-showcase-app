package dev.yacsa.network.impl

import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dev.yacsa.network.impl.di.NetworkModule
import dev.yacsa.network.impl.service.BooksApiService
import dev.yacsa.network.impl.utils.loadFileText
import dev.yacsa.network.model.BookNetModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

/**
 * https://dzone.com/articles/7-popular-unit-test-naming
 */
@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class TestBooksApiService {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private var mockWebServer = MockWebServer()

    private lateinit var apiService: BooksApiService

    @Inject
    lateinit var converter: Converter.Factory

    @Inject
    lateinit var moshi: Moshi

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

    @Test
    fun `(getBook) when response is okay expect  success`() = runTest {
        //region Arrange
        val source = loadFileText(this, "/book_success.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(source)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act
        val expected = moshi.adapter(BookNetModel::class.java).fromJson(source)
        val actual = apiService.getBook(1)
        //endregion Act

        //region Assert
        assertEquals(expected, actual)
        //endregion Assert
    }

    @Test
    fun `(getBook) when response is not okay expect exception`() = runTest {
        //region Arrange
        val source = ""
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(source)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act & Assert
        assertFailsWith(Exception::class) {
            apiService.getBook(1)
        }
        //endregion Assert
    }

    @Test
    fun `(getBook) when response is to long expect timeout exception`() = runTest {
        //region Arrange
        val source = ""
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBodyDelay(10_001L, TimeUnit.MILLISECONDS)
            .setBody(source)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act & Assert
        assertFailsWith(Exception::class) {
            apiService.getBook(1)
        }
        //endregion Assert
    }

    @Test
    fun `(getBook) when response is not 200 expect HttpException`() = runTest {
        //region Arrange
        val source = ""
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(source)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act & Assert
        assertFailsWith(HttpException::class) {
            apiService.getBook(1)
        }
        //endregion Assert
    }

    @Test
    fun `(getBook) when response body is illegal expect nullable fields`() = runTest {
        //region Arrange
        val sourceBooksPaged = loadFileText(this, "/books_page_success.json")
        val sourceBooks = loadFileText(this, "/book_success.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(sourceBooksPaged)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act
        val expected = moshi.adapter(BookNetModel::class.java).fromJson(sourceBooks).toString()
        val actual = apiService.getBook(1).toString()
        //endregion Act

        //region Act & Assert
        assertNotEquals(expected, actual)
        //endregion Act & Assert
    }

    @Test
    fun `(getBook) when response body is empty expect nullable fields`() = runTest {
        //region Arrange
        val sourceNullable = "{}"
        val sourceBooks = loadFileText(this, "/book_success.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(sourceNullable)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act
        val expected = moshi.adapter(BookNetModel::class.java).fromJson(sourceBooks).toString()
        val actual = apiService.getBook(1).toString()
        //endregion Act

        //region Act & Assert
        assertNotEquals(expected, actual)
        //endregion Act & Assert
    }

    @Test
    fun `(getBook) when response body is corrupted expect nullable fields`() = runTest {
        //region Arrange
        val sourceBookSuccess = loadFileText(this, "/book_success.json")
        val sourceBooksFail = loadFileText(this, "/book_fail.json")
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(sourceBooksFail)
        mockWebServer.enqueue(response)
        //endregion Arrange

        //region Act
        val expected = moshi.adapter(BookNetModel::class.java).fromJson(sourceBookSuccess).toString()
        val actual = apiService.getBook(1).toString()
        //endregion Act

        //region Act & Assert
        assertNotEquals(expected, actual)
        //endregion Act & Assert
    }
}

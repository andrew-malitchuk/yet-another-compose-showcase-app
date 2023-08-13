package dev.yacsa.database.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dev.yacsa.database.impl.dao.BookDbDao
import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.model.FormatsDbModel
import io.github.serpro69.kfaker.Faker
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class TestDb {

    private lateinit var database: YacsaDb
    private lateinit var bookDbDao: BookDbDao

    private val faker = Faker()

    private val value = mockk<BookDbModel>().apply {
        every { id } returns 1
        every { title } returns faker.quote.fortuneCookie()
        every { subjects } returns listOf(faker.quote.fortuneCookie())
        every { authors } returns emptyList()
        every { translators } returns emptyList()
        every { bookshelves } returns listOf(faker.quote.fortuneCookie())
        every { languages } returns listOf(faker.quote.fortuneCookie())
        every { copyright } returns true
        every { mediaType } returns null
        every { formats } returns FormatsDbModel(null, null, null, null, null, null, null, null)
        every { downloadCount } returns faker.hashCode()
        every { favourite } returns true
        every { page } returns 1
    }

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            YacsaDb::class.java,
        ).allowMainThreadQueries().build()

        bookDbDao = database.getBookDao()
    }

//    @After
//    fun closeDatabase() {
//        database.close()
//    }

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

    @Test
    fun `(insert) when value is correct should db not empty`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        //endregion Arrange

        //region Act
        bookDbDao.insert(value)
        val dbIsEmpty = bookDbDao.get().isNullOrEmpty()
        //endregion Act

        //region Assert
        assertFalse(dbIsEmpty)
        //endregion Assert
    }

    @Test
    fun `(get) when db is not empty should be at least 1 record`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        //endregion Arrange

        //region Act
        bookDbDao.insert(value)
        val dbIsEmpty = bookDbDao.get().isNullOrEmpty()
        //endregion Act

        //region Assert
        assertFalse(dbIsEmpty)
        //endregion Assert
    }

    @Test
    fun `(get) when db contains desired record should be returned this element`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        bookDbDao.insert(value)
        //endregion Arrange

        //region Act
        val actual = bookDbDao.get(value.id)
        //endregion Act

        //region Assert
        assertEquals(value, actual)
        //endregion Assert
    }

    @Test
    fun `(update) when record is updated should be updated record`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        bookDbDao.insert(value)
        //endregion Arrange

        //region Act
        val expected = mockk<BookDbModel>().apply {
            every { id } returns 1
            every { title } returns faker.quote.fortuneCookie()
            every { subjects } returns listOf(faker.quote.fortuneCookie())
            every { authors } returns emptyList()
            every { translators } returns emptyList()
            every { bookshelves } returns listOf(faker.quote.fortuneCookie())
            every { languages } returns listOf(faker.quote.fortuneCookie())
            every { copyright } returns true
            every { mediaType } returns null
            every { formats } returns FormatsDbModel(null, null, null, null, null, null, null, null)
            every { downloadCount } returns faker.hashCode()
            every { favourite } returns true
            every { page } returns 1
        }
        bookDbDao.update(value)
        val actual = bookDbDao.get(value.id)
        //endregion Act

        //region Assert
        assertEquals(value, actual)
        //endregion Assert
    }

    @Test
    fun `(deleteAll) when deleteAll calls should be no record`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        bookDbDao.insert(value)
        //endregion Arrange

        //region Act
        bookDbDao.deleteAll()
        val dbIsEmpty = bookDbDao.get().isNullOrEmpty()
        //endregion Act

        //region Assert
        assertTrue(dbIsEmpty)
        //endregion Assert
    }

    @Test
    fun `(deleteAll) when the last record is deleted should be no record`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        bookDbDao.insert(value)
        //endregion Arrange

        //region Act
        bookDbDao.delete(value.id)
        val dbIsEmpty = bookDbDao.get().isNullOrEmpty()
        //endregion Act

        //region Assert
        assertTrue(dbIsEmpty)
        //endregion Assert
    }

    @Test
    fun `(search) when desired record is in db should be result of search`() = runTest {
        //region Arrange
        bookDbDao.deleteAll()
        bookDbDao.insert(value)
        //endregion Arrange

        //region Act
        val actual = bookDbDao.search(value.title ?: "", null).firstOrNull()
        //endregion Act

        //region Assert
        assertEquals(value, actual)
        //endregion Assert
    }
}

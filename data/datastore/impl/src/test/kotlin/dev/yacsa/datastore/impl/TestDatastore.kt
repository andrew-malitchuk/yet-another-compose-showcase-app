package dev.yacsa.datastore.impl

import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class TestDatastore {

    private val faker = Faker()


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
package dev.yacsa.network.impl

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dev.yacsa.network.impl.di.FooModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@UninstallModules(FooModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var foo: String

    @Before
    fun init() {
        hiltRule.inject()
    }


    @Test
    fun testSomething() {
        assertThat(foo, containsString("bar"))
    }

}
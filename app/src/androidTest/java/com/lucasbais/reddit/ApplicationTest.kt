package com.lucasbais.reddit

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.lucasbais.reddit.di.AppInjector
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ApplicationTest {
    @Test
    fun whenGetAppContextThePackageIsCorrect() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.lucasbais.reddit", appContext.packageName)
    }

    @Test
    fun whenUseAppInjectorAndApplicationContextThenAppComponentIsCreated() {
        val appComponent =
            AppInjector.init(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as RedditApp)
        assertThat(appComponent, notNullValue())
    }
}

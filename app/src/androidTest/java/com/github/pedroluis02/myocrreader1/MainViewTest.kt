package com.github.pedroluis02.myocrreader1

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.pedroluis02.myocrreader1.ui.MainView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
class MainViewTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun before() {
        composeTestRule.apply {
            setContent {
                val navController = rememberNavController()
                MainView(navController = navController)
            }
        }
    }

    private fun hasText(@StringRes resId: Int) = hasText(composeTestRule.activity.getString(resId))

    @Test
    fun testInitialView() {
        composeTestRule.onNode(hasText(R.string.title)).assertIsDisplayed()
        composeTestRule.onNode(hasText(R.string.openCamera)).assertIsDisplayed()
    }
}
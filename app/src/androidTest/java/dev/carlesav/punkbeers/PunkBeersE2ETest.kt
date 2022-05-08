package dev.carlesav.punkbeers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.carlesav.catalog_presentation.catalog_detail.CatalogDetailScreen
import dev.carlesav.catalog_presentation.catalog_list.CatalogListScreen
import dev.carlesav.core.navigation.Route
import dev.carlesav.punkbeers.ui.theme.PunkBeersTheme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PunkBeersE2ETest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.setContent {
            PunkBeersTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.CATALOG_LIST
                    ) {
                        composable(Route.CATALOG_LIST) {
                            CatalogListScreen(onItemClick = { beer ->
                                navController.navigate("${Route.CATALOG_DETAIL}/${beer.id}")
                            })
                        }
                        composable(
                            route = "${Route.CATALOG_DETAIL}/{itemId}",
                            arguments = listOf(
                                navArgument("itemId") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val itemId = it.arguments?.getInt("itemId")!!
                            CatalogDetailScreen(beerId = itemId) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun testCatalogListAndGoToBeerDetail() = runTest {
        composeRule
            .onNodeWithTag("beer1")
            .assertExists()

        composeRule
            .onNodeWithTag("beer1")
            .performClick()

        composeRule
            .onNodeWithTag("Buzz")
            .assertExists()
    }
}
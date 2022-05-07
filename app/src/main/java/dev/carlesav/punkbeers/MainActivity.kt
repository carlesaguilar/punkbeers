package dev.carlesav.punkbeers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.carlesav.catalog_presentation.catalog_detail.CatalogDetailScreen
import dev.carlesav.catalog_presentation.catalog_list.CatalogListScreen
import dev.carlesav.core.navigation.Route
import dev.carlesav.punkbeers.ui.theme.PunkBeersTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                            CatalogDetailScreen(beerId = itemId)
                        }
                    }
                }
            }
        }
    }
}
package dev.carlesav.catalog_presentation.catalog_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dev.carlesav.catalog_presentation.catalog_detail.components.BeerDetailComponent
import dev.carlesav.catalog_presentation.components.ErrorComponent
import dev.carlesav.catalog_presentation.components.LoadingComponent

@Composable
fun CatalogDetailScreen(
    viewModel: CatalogDetailViewModel = hiltViewModel(),
    beerId: Int,
    onBackPressed: () -> Unit,
) {
    LaunchedEffect(key1 = LocalContext) {
        viewModel.getBeerDetail(beerId)
    }

    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.DarkGray)
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when {
                state.isLoading -> {
                    LoadingComponent(modifier = Modifier.fillMaxSize())
                }
                state.error.isNotEmpty() -> {
                    ErrorComponent(message = state.error)
                }
                state.beer != null -> {
                    BeerDetailComponent(state.beer)
                }
            }
        }
    }
}
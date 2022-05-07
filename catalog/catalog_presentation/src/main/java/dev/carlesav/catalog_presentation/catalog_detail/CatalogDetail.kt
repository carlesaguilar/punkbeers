package dev.carlesav.catalog_presentation.catalog_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogDetailScreen(
    viewModel: CatalogDetailViewModel = hiltViewModel(),
    beerId: Int,
) {
    LaunchedEffect(key1 = LocalContext) {
        viewModel.getBeerDetail(beerId)
    }
}
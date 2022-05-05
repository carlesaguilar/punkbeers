package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogListScreen(
    viewModel: CatalogListViewModel = hiltViewModel(),
) {
    viewModel.getBeers()
}
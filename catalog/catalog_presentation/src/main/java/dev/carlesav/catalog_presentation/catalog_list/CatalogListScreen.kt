package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_presentation.catalog_list.components.CatalogItems
import dev.carlesav.catalog_presentation.catalog_list.components.SearchBar
import dev.carlesav.catalog_presentation.components.ErrorComponent

@Composable
fun CatalogListScreen(
    viewModel: CatalogListViewModel = hiltViewModel(),
    onItemClick: (Beer) -> Unit,
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                SearchBar { query ->
                    viewModel.onEvent(CatalogListEvents.OnSearchQueryChange(query))
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
        ) {
            when {
                state.error.isNotEmpty() -> {
                    ErrorComponent(message = state.error)
                }
                else -> {
                    CatalogItems(state,
                        onItemClick = { beer ->
                            onItemClick(beer)
                        },
                        endReached = {
                            viewModel.onEvent(CatalogListEvents.LoadMore)
                        })
                }
            }
        }
    }
}

package dev.carlesav.catalog_presentation.catalog_list.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_presentation.catalog_list.CatalogListState
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun CatalogItems(
    state: CatalogListState,
    onItemClick: (Beer) -> Unit,
    endReached: (Boolean) -> Unit,
) {
    val spacing = LocalSpacing.current
    val listState = rememberLazyListState()

    if (state.items.isNotEmpty()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = spacing.spaceSmall,
                    bottom = spacing.spaceSmall,
                    start = spacing.spaceExtraSmall,
                    end = spacing.spaceExtraSmall
                )
        ) {
            items(state.items.count()) { index ->
                if (index == state.items.size - 1 && !state.endReached) {
                    Log.d("XXX", "*** endReached")
                    endReached(true)
                }
                CatalogItem(item = state.items[index]) { beer ->
                    onItemClick(beer)
                }
            }
            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = dev.carlesav.core.R.string.no_results),
                style = MaterialTheme.typography.h5,
                color = Color.White)
        }
    }
}

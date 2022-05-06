package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun CatalogItems(items: List<Beer>) {
    val spacing = LocalSpacing.current

    if (items.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = spacing.spaceSmall,
                    bottom = spacing.spaceSmall,
                    start = spacing.spaceExtraSmall,
                    end = spacing.spaceExtraSmall
                )
        ) {
            items(items.count()) { index ->
                CatalogItem(item = items[index]) {
                    // todo click
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
            Text("No results.",
                style = MaterialTheme.typography.h5,
                color = Color.White)
        }
    }
}

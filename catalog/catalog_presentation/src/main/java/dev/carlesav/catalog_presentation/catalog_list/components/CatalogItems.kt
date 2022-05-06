package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun CatalogItems(items: List<Beer>) {
    val spacing = LocalSpacing.current

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
}

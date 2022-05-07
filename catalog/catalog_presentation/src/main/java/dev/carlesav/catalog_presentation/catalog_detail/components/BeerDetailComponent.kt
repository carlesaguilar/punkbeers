package dev.carlesav.catalog_presentation.catalog_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_presentation.components.BeerImageComponent

@Composable
fun BeerDetailComponent(beer: Beer) {
    BeerImageComponent(
        imageUrl = beer.image_url,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    )
}
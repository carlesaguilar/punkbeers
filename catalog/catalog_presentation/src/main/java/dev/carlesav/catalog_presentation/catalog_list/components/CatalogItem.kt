package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.carlesav.catalog_domain.model.*
import dev.carlesav.catalog_presentation.components.BeerImageComponent
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun CatalogItem(
    beer: Beer,
    onItemClick: (Beer) -> Unit,
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = Modifier
            .padding(spacing.spaceSmall)
            .fillMaxWidth()
            .clickable {
                onItemClick(beer)
            }
            .testTag("beer${beer.id}")
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
        ) {
            Column {
                BeerImageComponent(
                    imageUrl = beer.image_url,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(
                        spacing.spaceSmall,
                        end = spacing.spaceLarge)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = beer.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = beer.description,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewCatalogItem() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        val beer = Beer(
            id = 1,
            name = "Beer",
            description = "Beer description",
            boil_volume = BoilVolume("", 0),
            food_pairing = emptyList(),
            ingredients = Ingredients(emptyList(), emptyList(), ""),
            method = Method(Fermentation(Temp(null, null)), emptyList(), null),
            volume = Volume("", 0),
            abv = null,
            attenuation_level = null,
            brewers_tips = null,
            contributed_by = null,
            ebc = null,
            first_brewed = null,
            ibu = null,
            image_url = null,
            ph = null,
            srm = null,
            tagline = null,
            target_fg = null,
            target_og = null,
        )

        CatalogItem(
            beer = beer,
            onItemClick = {}
        )
    }
}

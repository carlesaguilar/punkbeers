package dev.carlesav.catalog_presentation.catalog_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.carlesav.catalog_domain.model.*
import dev.carlesav.catalog_presentation.components.BeerImageComponent
import dev.carlesav.core.R
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun BeerDetailComponent(beer: Beer) {
    val spacing = LocalSpacing.current

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .padding(spacing.spaceMedium)) {

        BeerImageComponent(
            imageUrl = beer.image_url,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        Text(text = beer.name,
            style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        Text(
            text = beer.description,
            style = MaterialTheme.typography.body1,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        if (beer.tagline != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = beer.tagline!!,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }

        if (beer.brewers_tips != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.brewers_tips),
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Text(
                text = beer.brewers_tips!!,
                style = MaterialTheme.typography.body1,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }

        if (beer.food_pairing.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.food_pairing),
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            beer.food_pairing.forEach {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "\u2022 $it",
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
    }
}

@Preview
@Composable
fun PreviewDetailComponent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        val beer = Beer(
            id = 1,
            name = "Beer",
            description = "Beer description",
            boil_volume = BoilVolume("", 0),
            food_pairing = listOf("beer paring 1", "beer paring 2", "beer paring 3"),
            ingredients = Ingredients(emptyList(), emptyList(), ""),
            method = Method(Fermentation(Temp(null, null)), emptyList(), null),
            volume = Volume("", 0),
            abv = null,
            attenuation_level = null,
            brewers_tips = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
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

        BeerDetailComponent(
            beer = beer
        )
    }
}

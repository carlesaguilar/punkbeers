package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_presentation.components.BeerImageComponent
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun CatalogItem(
    item: Beer,
    onItemClick: (Beer) -> Unit,
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = Modifier
            .padding(spacing.spaceSmall)
            .fillMaxWidth()
            .clickable {
                onItemClick(item)
            }
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
        ) {
            Column {
                BeerImageComponent(
                    imageUrl = item.image_url,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                )
            }

            Column(
                modifier = Modifier.padding(
                    spacing.spaceSmall,
                    end = spacing.spaceLarge)
            ) {
                Text(
                    text = item.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Text(
                    text = item.description,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

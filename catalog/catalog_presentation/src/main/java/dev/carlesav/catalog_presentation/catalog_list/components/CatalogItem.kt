package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.R
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
                //onCharacterClick(character)
            }
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
        ) {
            Column {
                if (item.image_url != null) {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp),
                        painter = rememberImagePainter(
                            data = item.image_url,
                            builder = {
                                placeholder(R.drawable.placeholder)
                                error(R.drawable.placeholder)
                            }
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Inside
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp),
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Inside
                    )
                }
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

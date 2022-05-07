package dev.carlesav.catalog_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import dev.carlesav.core.R

@Composable
fun BeerImageComponent(
    imageUrl: String?,
    modifier: Modifier,
) {
    if (imageUrl != null) {
        Image(
            modifier = modifier,
            painter = rememberImagePainter(
                data = imageUrl,
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
            modifier = modifier,
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Inside
        )
    }
}
package dev.carlesav.catalog_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
fun PreviewBeerImageComponent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        BeerImageComponent(
            imageUrl = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

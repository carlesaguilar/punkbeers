package dev.carlesav.catalog_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.carlesav.core_ui.LocalSpacing

@Composable
fun ErrorComponent(message: String) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = message)
    }
}

@Preview
@Composable
fun PreviewErrorComponent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        ErrorComponent(message = "Lorem ipsum.")
    }
}
package dev.carlesav.catalog_presentation.catalog_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(onQueryChange: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    var searchFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        value = searchFieldState,
        onValueChange = {
            searchFieldState = it
            onQueryChange(it)
        },
        singleLine = true,
        placeholder = {
            Text("Search...")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Color.White)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}
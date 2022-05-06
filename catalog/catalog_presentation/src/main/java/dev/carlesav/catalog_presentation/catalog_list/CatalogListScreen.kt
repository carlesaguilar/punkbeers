package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.carlesav.catalog_presentation.catalog_list.components.CatalogItems
import dev.carlesav.catalog_presentation.components.ErrorComponent
import dev.carlesav.catalog_presentation.components.LoadingComponent

@Composable
fun CatalogListScreen(
    viewModel: CatalogListViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    var searchFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    value = searchFieldState,
                    onValueChange = {
                        searchFieldState = it
                        viewModel.onEvent(CatalogListEvents.OnSearchQueryChange(searchFieldState))
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
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
        ) {
            when {
                state.isLoading -> {
                    LoadingComponent(modifier = Modifier.fillMaxSize())
                }
                state.error.isNotEmpty() -> {
                    ErrorComponent(message = state.error)
                }
                else -> {
                    CatalogItems(state.items)
                }
            }
        }
    }
}

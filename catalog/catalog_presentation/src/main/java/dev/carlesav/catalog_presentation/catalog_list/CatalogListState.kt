package dev.carlesav.catalog_presentation.catalog_list

import dev.carlesav.catalog_domain.model.Beer

data class CatalogListState(
    val items: List<Beer> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val error: String = "",
)

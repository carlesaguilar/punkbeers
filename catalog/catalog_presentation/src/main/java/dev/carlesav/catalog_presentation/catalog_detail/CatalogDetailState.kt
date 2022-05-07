package dev.carlesav.catalog_presentation.catalog_detail

import dev.carlesav.catalog_domain.model.Beer

data class CatalogDetailState(
    val beer: Beer? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)

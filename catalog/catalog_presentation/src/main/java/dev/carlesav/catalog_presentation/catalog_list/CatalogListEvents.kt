package dev.carlesav.catalog_presentation.catalog_list

sealed class CatalogListEvents {
    data class OnSearchQueryChange(val query: String) : CatalogListEvents()
}

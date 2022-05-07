package dev.carlesav.catalog_domain.model

data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String?,
)
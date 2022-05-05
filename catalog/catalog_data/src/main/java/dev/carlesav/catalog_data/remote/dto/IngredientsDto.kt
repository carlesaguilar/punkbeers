package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class IngredientsDto(
    @field:Json(name = "hops")
    val hops: List<HopDto>,
    @field:Json(name = "malt")
    val malt: List<MaltDto>,
    @field:Json(name = "yeast")
    val yeast: String,
)
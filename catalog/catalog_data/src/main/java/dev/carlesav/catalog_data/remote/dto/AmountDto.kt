package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class AmountDto(
    @field:Json(name = "unit")
    val unit: String,
    @field:Json(name = "value")
    val value: Double,
)
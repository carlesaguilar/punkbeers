package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class MaltDto(
    @field:Json(name = "amount")
    val amount: AmountDto,
    @field:Json(name = "name")
    val name: String,
)
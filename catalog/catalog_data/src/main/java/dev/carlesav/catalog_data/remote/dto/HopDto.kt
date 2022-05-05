package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class HopDto(
    @field:Json(name = "add")
    val add: String,
    @field:Json(name = "amount")
    val amount: AmountDto,
    @field:Json(name = "attribute")
    val attribute: String,
    @field:Json(name = "name")
    val name: String,
)
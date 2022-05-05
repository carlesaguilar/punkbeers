package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class FermentationDto(
    @field:Json(name = "temp")
    val temp: TempDto,
)
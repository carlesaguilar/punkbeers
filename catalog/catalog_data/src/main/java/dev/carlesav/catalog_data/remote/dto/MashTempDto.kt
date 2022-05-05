package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class MashTempDto(
    @field:Json(name = "duration")
    val duration: Int?,
    @field:Json(name = "temp")
    val temp: TempDto,
)
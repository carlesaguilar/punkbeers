package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class MethodDto(
    @field:Json(name = "fermentation")
    val fermentation: FermentationDto,
    @field:Json(name = "mash_temp")
    val mash_temp: List<MashTempDto>,
    @field:Json(name = "twist")
    val twist: String?,
)
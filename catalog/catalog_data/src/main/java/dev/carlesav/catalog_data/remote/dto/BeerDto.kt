package dev.carlesav.catalog_data.remote.dto

import com.squareup.moshi.Json

data class BeerDto(
    @field:Json(name = "abv")
    val abv: Double?,
    @field:Json(name = "attenuation_level")
    val attenuation_level: Double?,
    @field:Json(name = "boil_volume")
    val boil_volume: BoilVolumeDto,
    @field:Json(name = "brewers_tips")
    val brewers_tips: String?,
    @field:Json(name = "contributed_by")
    val contributed_by: String?,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "ebc")
    val ebc: Double?,
    @field:Json(name = "first_brewed")
    val first_brewed: String?,
    @field:Json(name = "food_pairing")
    val food_pairing: List<String>,
    @field:Json(name = "ibu")
    val ibu: Double?,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image_url")
    val image_url: String?,
    @field:Json(name = "ingredients")
    val ingredients: IngredientsDto,
    @field:Json(name = "method")
    val method: MethodDto,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "ph")
    val ph: Double?,
    @field:Json(name = "srm")
    val srm: Double?,
    @field:Json(name = "tagline")
    val tagline: String?,
    @field:Json(name = "target_fg")
    val target_fg: Double?,
    @field:Json(name = "target_og")
    val target_og: Double?,
    @field:Json(name = "volume")
    val volume: VolumeDto,
)
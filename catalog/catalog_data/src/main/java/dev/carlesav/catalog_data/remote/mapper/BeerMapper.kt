package dev.carlesav.catalog_data.remote.mapper

import dev.carlesav.catalog_data.remote.dto.*
import dev.carlesav.catalog_domain.model.*

fun BeerDto.toBeer() = Beer(
    abv = this.abv,
    attenuation_level = this.attenuation_level,
    boil_volume = this.boil_volume.toBoilVolume(),
    brewers_tips = this.brewers_tips,
    contributed_by = this.contributed_by,
    description = this.description,
    ebc = this.ebc,
    first_brewed = this.first_brewed,
    food_pairing = this.food_pairing,
    ibu = this.ibu,
    id = this.id,
    image_url = this.image_url,
    ingredients = this.ingredients.toIngredients(),
    method = this.method.toMethod(),
    name = this.name,
    ph = this.ph,
    srm = this.srm,
    tagline = this.tagline,
    target_fg = this.target_fg,
    target_og = this.target_og,
    volume = this.volume.toVolume()
)

fun AmountDto.toAmount() = Amount(
    unit = this.unit,
    value = this.value
)

fun BoilVolumeDto.toBoilVolume() = BoilVolume(
    unit = this.unit,
    value = this.value
)

fun FermentationDto.toFermentation() = Fermentation(
    temp = this.temp.toTemp()
)

fun HopDto.toHop() = Hop(
    add = this.add,
    amount = this.amount.toAmount(),
    attribute = this.attribute,
    name = this.name
)

fun MaltDto.toMalt() = Malt(
    amount = this.amount.toAmount(),
    name = this.name
)

fun IngredientsDto.toIngredients() = Ingredients(
    hops = this.hops.map { it.toHop() },
    malt = this.malt.map { it.toMalt() },
    yeast = this.yeast
)

fun TempDto.toTemp() = Temp(
    unit = this.unit,
    value = this.value
)

fun MashTempDto.toMashTemp() = MashTemp(
    duration = this.duration,
    temp = this.temp.toTemp()
)

fun MethodDto.toMethod() = Method(
    fermentation = this.fermentation.toFermentation(),
    mash_temp = this.mash_temp.map { it.toMashTemp() },
    twist = this.twist ?: ""
)

fun VolumeDto.toVolume() = Volume(
    unit = this.unit,
    value = this.value
)
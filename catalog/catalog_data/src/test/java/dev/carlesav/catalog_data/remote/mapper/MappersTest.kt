package dev.carlesav.catalog_data.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dev.carlesav.catalog_data.remote.dto.*
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.catalogListResponse
import org.junit.Before
import org.junit.Test

class MappersTest {
    private lateinit var beerDto: BeerDto
    private lateinit var beer: Beer

    @Before
    fun setup() {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(MutableList::class.java, BeerDto::class.java)
        val adapter = moshi.adapter<List<BeerDto>>(type)
        val list = adapter.fromJson(catalogListResponse)!!
        beerDto = list[0]
        beer = beerDto.toBeer()
    }

    @Test
    fun `map BoilVolumeDto to BoilVolume`() {
        assertThat(beer.boil_volume.unit).isEqualTo(beerDto.boil_volume.unit)
        assertThat(beer.boil_volume.value).isEqualTo(beerDto.boil_volume.value)
    }

    @Test
    fun `map AmountDto to Amount`() {
        val amountDto = AmountDto("unit", 1.3)
        val amount = amountDto.toAmount()
        assertThat(amount.unit).isEqualTo(amountDto.unit)
        assertThat(amount.value).isEqualTo(amountDto.value)
    }

    @Test
    fun `map HopDto to Hop`() {
        assertThat(beer.ingredients.hops[0].add).isEqualTo(beerDto.ingredients.hops[0].add)
        assertThat(beer.ingredients.hops[0].attribute).isEqualTo(beerDto.ingredients.hops[0].attribute)
        assertThat(beer.ingredients.hops[0].name).isEqualTo(beerDto.ingredients.hops[0].name)
    }

    @Test
    fun `map MaltDto to Malt`() {
        assertThat(beer.ingredients.malt[0].name).isEqualTo(beerDto.ingredients.malt[0].name)
    }

    @Test
    fun `map IngredientsDto to Ingredients`() {
        assertThat(beer.ingredients.yeast).isEqualTo(beerDto.ingredients.yeast)
    }

    @Test
    fun `map TempDto to Temp`() {
        val tempDto = TempDto("unit", 2)
        val temp = tempDto.toTemp()
        assertThat(temp.unit).isEqualTo(tempDto.unit)
        assertThat(temp.value).isEqualTo(tempDto.value)
    }

    @Test
    fun `map MashTempDto to MashTemp`() {
        val mashTempDto = MashTempDto(3, TempDto(null, null))
        val mashTemp = mashTempDto.toMashTemp()
        assertThat(mashTemp.duration).isEqualTo(mashTempDto.duration)
    }

    @Test
    fun `map MethodDto to Method`() {
        assertThat(beer.method.twist).isEqualTo(beerDto.method.twist)
    }

    @Test
    fun `map VolumeDto to Volume`() {
        val volumeDto = VolumeDto("unit", 1)
        val volume = volumeDto.toVolume()
        assertThat(volume.unit).isEqualTo(volumeDto.unit)
        assertThat(volume.value).isEqualTo(volumeDto.value)
    }

    @Test
    fun `map BeerDto to Beer`() {
        assertThat(beer.abv).isEqualTo(beerDto.abv)
        assertThat(beer.attenuation_level).isEqualTo(beerDto.attenuation_level)
        assertThat(beer.brewers_tips).isEqualTo(beerDto.brewers_tips)
        assertThat(beer.contributed_by).isEqualTo(beerDto.contributed_by)
        assertThat(beer.description).isEqualTo(beerDto.description)
        assertThat(beer.ebc).isEqualTo(beerDto.ebc)
        assertThat(beer.first_brewed).isEqualTo(beerDto.first_brewed)
        assertThat(beer.ibu).isEqualTo(beerDto.ibu)
        assertThat(beer.id).isEqualTo(beerDto.id)
        assertThat(beer.image_url).isEqualTo(beerDto.image_url)
        assertThat(beer.name).isEqualTo(beerDto.name)
        assertThat(beer.ph).isEqualTo(beerDto.ph)
        assertThat(beer.srm).isEqualTo(beerDto.srm)
        assertThat(beer.tagline).isEqualTo(beerDto.tagline)
        assertThat(beer.target_fg).isEqualTo(beerDto.target_fg)
        assertThat(beer.target_og).isEqualTo(beerDto.target_og)
    }
}

package dev.carlesav.catalog_data.remote

import dev.carlesav.catalog_data.remote.dto.BeerDto
import retrofit2.Response
import retrofit2.http.GET

interface PunkApi {
    companion object {
        private const val CATALOG_LIST = "beers"
    }

    @GET(CATALOG_LIST)
    suspend fun getBeers(): Response<List<BeerDto>>
}
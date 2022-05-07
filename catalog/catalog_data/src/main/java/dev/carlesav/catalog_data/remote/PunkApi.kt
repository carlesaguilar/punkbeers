package dev.carlesav.catalog_data.remote

import dev.carlesav.catalog_data.remote.dto.BeerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PunkApi {
    companion object {
        private const val CATALOG_LIST = "beers"
        private const val CATALOG_DETAIL = "beers/{beer_id}"
    }

    @GET(CATALOG_LIST)
    suspend fun getBeers(
        @Query("page") page: Int,
    ): Response<List<BeerDto>>

    @GET(CATALOG_LIST)
    suspend fun searchBeers(
        @Query("beer_name") name: String,
        @Query("page") page: Int,
    ): Response<List<BeerDto>>

    @GET(CATALOG_DETAIL)
    suspend fun getBeerDetail(
        @Path("beer_id") beerId: Int,
    ): Response<List<BeerDto>>
}
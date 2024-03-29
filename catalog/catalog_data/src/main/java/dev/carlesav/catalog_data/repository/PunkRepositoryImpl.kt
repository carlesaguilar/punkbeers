package dev.carlesav.catalog_data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.carlesav.catalog_data.remote.PunkApi
import dev.carlesav.catalog_data.remote.mapper.toBeer
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo
import dev.carlesav.catalog_domain.repository.PunkRepository
import org.json.JSONObject

class PunkRepositoryImpl(
    private val api: PunkApi,
) : PunkRepository {
    override suspend fun getBeers(query: String, page: Int): Either<FailureBo, List<Beer>> {
        val response = if (query.isEmpty()) {
            api.getBeers(page)
        } else {
            api.searchBeers(query, page)
        }

        return if (response.isSuccessful) {
            val responseMap = response.body()?.map { it.toBeer() } ?: emptyList()
            responseMap.right()
        } else {
            val jsonError = JSONObject(response.errorBody()?.string())
            val message = "${jsonError.get("error")}: ${jsonError.get("message")}"
            FailureBo(message).left()
        }
    }

    override suspend fun getBeerDetail(beerId: Int): Either<FailureBo, Beer> {
        val response = api.getBeerDetail(beerId)
        return if (response.isSuccessful) {
            val beer = response.body()?.get(0)!!.toBeer()
            beer.right()
        } else {
            val jsonError = JSONObject(response.errorBody()?.string())
            val message = "${jsonError.get("error")}: ${jsonError.get("message")}"
            FailureBo(message).left()
        }
    }
}
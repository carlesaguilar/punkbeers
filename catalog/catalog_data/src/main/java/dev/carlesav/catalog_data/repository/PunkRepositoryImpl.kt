package dev.carlesav.catalog_data.repository

import dev.carlesav.catalog_data.remote.PunkApi
import dev.carlesav.catalog_data.remote.mapper.toBeer
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class PunkRepositoryImpl(
    private val api: PunkApi,
) : PunkRepository {
    override fun getBeers(query: String, page: Int): Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val response = if (query.isEmpty()) {
            api.getBeers(page)
        } else {
            api.searchBeers(query, page)
        }
        emit(Resource.Loading(isLoading = false))
        if (response.isSuccessful) {
            val responseMap = response.body()?.map { it.toBeer() } ?: emptyList()
            emit(Resource.Success(responseMap))
        } else {
            val jsonError = JSONObject(response.errorBody()?.string())
            val message = "${jsonError.get("error")}: ${jsonError.get("message")}"
            emit(Resource.Error(message))
        }
    }

    override fun getBeerDetail(beerId: Int): Flow<Resource<Beer>> = flow {
        emit(Resource.Loading(isLoading = true))

        val response = api.getBeerDetail(beerId)
        emit(Resource.Loading(isLoading = false))
        if (response.isSuccessful) {
            val beer = response.body()?.get(0)!!.toBeer()
            emit(Resource.Success(beer))
        } else {
            val jsonError = JSONObject(response.errorBody()?.string())
            val message = "${jsonError.get("error")}: ${jsonError.get("message")}"
            emit(Resource.Error(message))
        }
    }
}
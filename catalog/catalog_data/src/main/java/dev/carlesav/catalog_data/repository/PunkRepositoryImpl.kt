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
    override fun getBeers(query: String): Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val response = if (query.isEmpty()) {
            api.getBeers()
        } else {
            api.searchBeers(query)
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
}
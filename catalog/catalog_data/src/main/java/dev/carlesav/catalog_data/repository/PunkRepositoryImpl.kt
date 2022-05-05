package dev.carlesav.catalog_data.repository

import dev.carlesav.catalog_data.remote.PunkApi
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PunkRepositoryImpl(
    private val api: PunkApi,
) : PunkRepository {
    override fun getBeers(): Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Loading())

        val response = api.getBeers()
        if (response.isSuccessful) {
            // todo map response and emit it
        } else {
            // todo catch error and emit it
        }
    }
}
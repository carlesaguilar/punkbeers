package dev.carlesav.catalog_domain.repository

import arrow.core.Either
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PunkRepository {
    suspend fun getBeers(query: String, page: Int): Either<FailureBo, List<Beer>>
    fun getBeerDetail(beerId: Int): Flow<Resource<Beer>>
}
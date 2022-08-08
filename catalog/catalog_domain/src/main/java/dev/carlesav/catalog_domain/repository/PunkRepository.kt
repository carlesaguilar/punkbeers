package dev.carlesav.catalog_domain.repository

import arrow.core.Either
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo

interface PunkRepository {
    suspend fun getBeers(query: String, page: Int): Either<FailureBo, List<Beer>>
    suspend fun getBeerDetail(beerId: Int): Either<FailureBo, Beer>
}
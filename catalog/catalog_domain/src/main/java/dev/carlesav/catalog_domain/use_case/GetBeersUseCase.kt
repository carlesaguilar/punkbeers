package dev.carlesav.catalog_domain.use_case

import arrow.core.Either
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo

interface GetBeersUseCase {
    suspend operator fun invoke(query: String, page: Int): Either<FailureBo, List<Beer>>
}
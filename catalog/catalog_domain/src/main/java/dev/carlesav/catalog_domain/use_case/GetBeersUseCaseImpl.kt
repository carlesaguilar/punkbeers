package dev.carlesav.catalog_domain.use_case

import arrow.core.Either
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo
import dev.carlesav.catalog_domain.repository.PunkRepository
import javax.inject.Inject

class GetBeersUseCaseImpl @Inject constructor(
    private val repository: PunkRepository,
) : GetBeersUseCase {
    override suspend fun invoke(query: String, page: Int): Either<FailureBo, List<Beer>> =
        repository.getBeers(query = query, page = page)
}
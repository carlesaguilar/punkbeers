package dev.carlesav.catalog_domain.use_case

import arrow.core.Either
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.model.FailureBo
import dev.carlesav.catalog_domain.repository.PunkRepository
import javax.inject.Inject

class GetBeerDetailUseCase @Inject constructor(
    private val repository: PunkRepository,
) {
    suspend operator fun invoke(beerId: Int): Either<FailureBo, Beer> =
        repository.getBeerDetail(beerId)
}
package dev.carlesav.catalog_domain.use_case

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeerDetailUseCaseImpl @Inject constructor(
    private val repository: PunkRepository,
) : GetBeerDetailUseCase {
    override fun invoke(beerId: Int): Flow<Resource<Beer>> {
        return repository.getBeerDetail(beerId)
    }
}
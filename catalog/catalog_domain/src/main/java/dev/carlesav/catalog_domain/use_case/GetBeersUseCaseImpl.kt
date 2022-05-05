package dev.carlesav.catalog_domain.use_case

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersUseCaseImpl @Inject constructor(
    private val repository: PunkRepository,
) : GetBeersUseCase {

    override fun invoke(): Flow<Resource<List<Beer>>> {
        return repository.getBeers()
    }
}
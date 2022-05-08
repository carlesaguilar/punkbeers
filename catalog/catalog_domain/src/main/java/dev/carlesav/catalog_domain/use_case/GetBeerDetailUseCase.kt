package dev.carlesav.catalog_domain.use_case

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetBeerDetailUseCase {
    operator fun invoke(beerId: Int): Flow<Resource<Beer>>
}
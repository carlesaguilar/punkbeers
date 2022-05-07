package dev.carlesav.catalog_domain.use_case

import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetBeerDetailUseCase<T> {
    operator fun invoke(beerId: Int): Flow<Resource<T>>
}
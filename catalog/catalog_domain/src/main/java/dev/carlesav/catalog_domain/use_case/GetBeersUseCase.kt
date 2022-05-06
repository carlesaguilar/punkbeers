package dev.carlesav.catalog_domain.use_case

import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetBeersUseCase<T> {
    operator fun invoke(query: String): Flow<Resource<T>>
}
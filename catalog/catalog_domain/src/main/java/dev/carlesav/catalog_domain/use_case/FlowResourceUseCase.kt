package dev.carlesav.catalog_domain.use_case

import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlowResourceUseCase<T> {
    operator fun invoke(): Flow<Resource<T>>
}
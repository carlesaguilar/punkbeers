package dev.carlesav.catalog_domain.use_case

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetBeersUseCase {
    operator fun invoke(): Flow<Resource<List<Beer>>>
}
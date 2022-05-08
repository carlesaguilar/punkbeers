package dev.carlesav.catalog_domain.use_case

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetBeersUseCase {
    operator fun invoke(query: String, page: Int): Flow<Resource<List<Beer>>>
}
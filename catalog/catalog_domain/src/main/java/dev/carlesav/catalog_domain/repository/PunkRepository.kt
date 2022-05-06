package dev.carlesav.catalog_domain.repository

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PunkRepository {
    fun getBeers(query: String): Flow<Resource<List<Beer>>>
}
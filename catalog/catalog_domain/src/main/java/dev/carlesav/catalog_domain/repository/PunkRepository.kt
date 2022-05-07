package dev.carlesav.catalog_domain.repository

import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PunkRepository {
    fun getBeers(query: String, page: Int): Flow<Resource<List<Beer>>>
    fun getBeerDetail(beerId: Int): Flow<Resource<Beer>>
}
package dev.carlesav.punkbeers.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dev.carlesav.catalog_data.remote.dto.BeerDto
import dev.carlesav.catalog_data.remote.mapper.toBeer
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.repository.PunkRepository
import dev.carlesav.core.catalogListResponse
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImpl : PunkRepository {
    override fun getBeers(query: String, page: Int): Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Success(listOf(getBeer())))
    }

    override fun getBeerDetail(beerId: Int): Flow<Resource<Beer>> = flow {
        emit(Resource.Success(getBeer()))
    }

    private fun getBeer(): Beer {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(MutableList::class.java, BeerDto::class.java)
        val adapter = moshi.adapter<List<BeerDto>>(type)
        val list = adapter.fromJson(catalogListResponse)!!
        return list[0].toBeer()
    }
}
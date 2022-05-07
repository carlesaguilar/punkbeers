package dev.carlesav.catalog_presentation.catalog_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.catalog_domain.model.Beer
import dev.carlesav.catalog_domain.use_case.GetBeerDetailUseCase
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatalogDetailViewModel @Inject constructor(
    private val getBeerDetailUseCase: GetBeerDetailUseCase<Beer>,
) : ViewModel() {

    var state by mutableStateOf(CatalogDetailState())

    fun getBeerDetail(beerId: Int) {
        getBeerDetailUseCase(beerId).onEach { result ->
            state = when (result) {
                is Resource.Loading -> {
                    state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    state.copy(beer = result.data)
                }
                is Resource.Error -> {
                    state.copy(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
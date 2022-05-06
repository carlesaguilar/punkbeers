package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.catalog_domain.use_case.GetBeersUseCaseImpl
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatalogListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCaseImpl,
) : ViewModel() {
    var state by mutableStateOf(CatalogListState())

    init {
        getBeers()
    }

    private fun getBeers() {
        getBeersUseCase().onEach { result ->
            state = when (result) {
                is Resource.Loading -> {
                    state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    state.copy(items = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    state.copy(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CatalogListEvents) {
        when (event) {
            is CatalogListEvents.OnSearchQueryChange -> {
            }
        }
    }
}
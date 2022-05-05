package dev.carlesav.catalog_presentation.catalog_list

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
    fun getBeers() {
        getBeersUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                }
                is Resource.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}
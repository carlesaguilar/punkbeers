package dev.carlesav.catalog_presentation.catalog_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.catalog_domain.use_case.GetBeerDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogDetailViewModel @Inject constructor(
    private val getBeerDetailUseCase: GetBeerDetailUseCase,
) : ViewModel() {

    var state by mutableStateOf(CatalogDetailState())

    fun getBeerDetail(beerId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getBeerDetailUseCase(beerId).fold({ error ->
                state = state.copy(
                    isLoading = false,
                    error = error.message
                )
            }, { beer ->
                state = state.copy(
                    isLoading = false,
                    beer = beer
                )
            })
        }
    }
}
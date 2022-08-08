package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.catalog_domain.use_case.GetBeersUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
) : ViewModel() {
    var state by mutableStateOf(CatalogListState())

    private val paginationInitialPage = 1
    private val paginationNumPageItems = 25

    private var searchJob: Job? = null

    init {
        getBeers()
    }

    @Synchronized
    private fun getBeers() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getBeersUseCase(state.searchQuery, state.page).fold({ error ->
                state = state.copy(
                    isLoading = false,
                    error = error.message
                )
            }, { beersList ->
                val noMoreItems = beersList.size < paginationNumPageItems
                state = if (state.onQueryChange) {
                    state.copy(
                        firstLoadCompleted = true,
                        onQueryChange = false,
                        items = beersList,
                        noMoreItems = noMoreItems
                    )
                } else {
                    state.copy(
                        firstLoadCompleted = true,
                        items = state.items + beersList,
                        endReached = false,
                        noMoreItems = noMoreItems
                    )
                }
            })
        }
    }

    @Synchronized
    fun onEvent(event: CatalogListEvents) {
        when (event) {
            is CatalogListEvents.OnSearchQueryChange -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    state = state.copy(
                        firstLoadCompleted = false,
                        items = emptyList(),
                        searchQuery = event.query,
                        onQueryChange = true,
                        page = paginationInitialPage,
                        noMoreItems = false,
                        endReached = false
                    )
                    getBeers()
                }
            }
            CatalogListEvents.LoadMore -> {
                if (state.noMoreItems || state.isLoading) {
                    return
                }
                state = state.copy(endReached = true, page = state.page + 1)
                getBeers()
            }
        }
    }
}
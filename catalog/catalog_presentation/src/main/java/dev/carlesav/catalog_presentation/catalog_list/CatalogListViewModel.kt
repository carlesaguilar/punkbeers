package dev.carlesav.catalog_presentation.catalog_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.catalog_domain.use_case.GetBeersUseCaseImpl
import dev.carlesav.core.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCaseImpl,
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
        getBeersUseCase(state.searchQuery, state.page).onEach { result ->
            state = when (result) {
                is Resource.Loading -> {
                    state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    val retrievedItems = result.data ?: emptyList()
                    val noMoreItems = retrievedItems.size < paginationNumPageItems
                    if (state.onQueryChange) {
                        state.copy(onQueryChange = false,
                            items = retrievedItems,
                            noMoreItems = noMoreItems)
                    } else {
                        state.copy(items = state.items + retrievedItems,
                            endReached = false,
                            noMoreItems = noMoreItems)
                    }
                }
                is Resource.Error -> {
                    state.copy(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    @Synchronized
    fun onEvent(event: CatalogListEvents) {
        when (event) {
            is CatalogListEvents.OnSearchQueryChange -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    state = state.copy(
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
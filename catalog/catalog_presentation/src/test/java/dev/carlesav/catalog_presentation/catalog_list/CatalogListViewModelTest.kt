package dev.carlesav.catalog_presentation.catalog_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.carlesav.catalog_domain.model.*
import dev.carlesav.catalog_domain.use_case.GetBeersUseCase
import dev.carlesav.core.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CatalogListViewModelTest {
    @RelaxedMockK
    private lateinit var getBeersUseCase: GetBeersUseCase

    private lateinit var viewModel: CatalogListViewModel
    private val beersList = listOf(Beer(
        id = 1,
        name = "Beer",
        description = "Beer description",
        boil_volume = BoilVolume("", 0),
        food_pairing = listOf("beer paring 1", "beer paring 2", "beer paring 3"),
        ingredients = Ingredients(emptyList(), emptyList(), ""),
        method = Method(Fermentation(Temp(null, null)), emptyList(), null),
        volume = Volume("", 0),
        abv = null,
        attenuation_level = null,
        brewers_tips = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        contributed_by = null,
        ebc = null,
        first_brewed = null,
        ibu = null,
        image_url = null,
        ph = null,
        srm = null,
        tagline = null,
        target_fg = null,
        target_og = null,
    ))

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test catalog detail viewmodel, search beer, return loading`() = runTest {
        coEvery { getBeersUseCase("", 1) } returns flow {
            emit(Resource.Loading(true))
        }
        viewModel = CatalogListViewModel(getBeersUseCase)
        assert(viewModel.state.isLoading)
    }

    @Test
    fun `test catalog detail viewmodel, search beer, return items`() = runTest {
        coEvery { getBeersUseCase("", 1) } returns flow {
            emit(Resource.Success(beersList))
        }
        viewModel = CatalogListViewModel(getBeersUseCase)
        assert(viewModel.state.firstLoadCompleted)
        assert(viewModel.state.items.isNotEmpty())
    }

    @Test
    fun `test catalog detail viewmodel, search beer, return error`() = runTest {
        coEvery { getBeersUseCase("", 1) } returns flow {
            emit(Resource.Error("error"))
        }
        viewModel = CatalogListViewModel(getBeersUseCase)
        assert(viewModel.state.error == "error")
    }
}

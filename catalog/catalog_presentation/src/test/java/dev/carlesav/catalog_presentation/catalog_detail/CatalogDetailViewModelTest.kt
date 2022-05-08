package dev.carlesav.catalog_presentation.catalog_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.carlesav.catalog_domain.model.*
import dev.carlesav.catalog_domain.use_case.GetBeerDetailUseCase
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
import org.junit.*

@ExperimentalCoroutinesApi
class CatalogDetailViewModelTest {
    @RelaxedMockK
    private lateinit var getBeerDetailUseCase: GetBeerDetailUseCase

    private lateinit var viewModel: CatalogDetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = CatalogDetailViewModel(getBeerDetailUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test catalog detail viewmodel, search beer, return loading`() = runTest {
        coEvery { getBeerDetailUseCase(beerId = 1) } returns flow {
            emit(Resource.Loading(true))
        }
        viewModel.getBeerDetail(1)
        assert(viewModel.state.isLoading)
        assert(viewModel.state.beer == null)
        assert(viewModel.state.error.isEmpty())
    }

    @Test
    fun `test catalog detail viewmodel, search beer, return error`() = runTest {
        coEvery { getBeerDetailUseCase(beerId = 1) } returns flow {
            emit(Resource.Error("error"))
        }
        viewModel.getBeerDetail(beerId = 1)
        Assert.assertFalse(viewModel.state.isLoading)
        assert(viewModel.state.beer == null)
        assert(viewModel.state.error == "error")
    }

    @Test
    fun `test character detail viewmodel, search character, return data`() = runTest {
        val beer = Beer(
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
        )

        coEvery { getBeerDetailUseCase(beerId = 1) } returns flow {
            emit(Resource.Success(beer))
        }
        viewModel.getBeerDetail(beerId = 1)
        Assert.assertFalse(viewModel.state.isLoading)
        assert(viewModel.state.beer == beer)
        assert(viewModel.state.error.isEmpty())
    }
}

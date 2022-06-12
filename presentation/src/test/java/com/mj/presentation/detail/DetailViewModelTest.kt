package com.mj.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mj.pokemonapp.R
import com.mj.domain.model.PokemonDetail
import com.mj.domain.model.PokemonLocation
import com.mj.domain.repository.PokemonRepository
import com.mj.domain.usecase.GetPokemonDetailUseCase
import com.mj.domain.usecase.GetPokemonLocationsUseCase
import com.mj.domain.utils.ResultOf
import com.mj.presentation.utils.MainCoroutineRule
import com.mj.presentation.utils.resourceprovider.ResourcesProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    var instantExceptionRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var pokemonRepository: PokemonRepository

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val getPokemonDetailUseCase = GetPokemonDetailUseCase(pokemonRepository)
        val getPokemonLocationsUseCase = GetPokemonLocationsUseCase(pokemonRepository)

        viewModel = DetailViewModel(getPokemonDetailUseCase, getPokemonLocationsUseCase, resourcesProvider)
    }

    @Test
    fun getPokemonDetailSuccessTest() = mainCoroutineRule.runBlockingTest {
        val expectedAnswer = PokemonDetail(1, 7, "Bulbasaur", "image_url_1", 69)

        Mockito.`when`(pokemonRepository.getPokemonDetail("1")).thenReturn(
            ResultOf.Success(expectedAnswer)
        )

        viewModel.getPokemonDetail(1)

        assertThat(viewModel.pokemonDetailLiveData.value).isEqualTo(expectedAnswer)
    }

    @Test
    fun getPokemonDetailFailTest() = mainCoroutineRule.runBlockingTest {
        val expectedAnswer = "error"

        Mockito.`when`(pokemonRepository.getPokemonDetail("1")).thenReturn(
            ResultOf.Error(Exception())
        )
        Mockito.`when`(resourcesProvider.getString(R.string.error_retrieving_pokemon_detail)).thenReturn(expectedAnswer)

        val job = launch {
            viewModel.errorFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(expectedAnswer)
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.getPokemonDetail(1)
        job.join()
        job.cancel()
    }

    @Test
    fun getPokemonLocationsSuccessTest1() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(pokemonRepository.getPokemonLocations()).thenReturn(
            ResultOf.Success(
                listOf(
                    PokemonLocation(1, 37.394919, 127.111138),
                    PokemonLocation(25, 37.350613, 127.173124),
                    PokemonLocation(25, 37.401250, 127.173124),
                    PokemonLocation(52, 37.401250, 127.110660),
                    PokemonLocation(52, 37.388999, 127.117476),
                    PokemonLocation(52, 37.388078, 127.121618)
                )
            )
        )

        viewModel.getPokemonLocations(1)
        assertThat(viewModel.pokemonLocationsLiveData.value?.size).isEqualTo(1)
    }

    @Test
    fun getPokemonLocationsSuccessTest2() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(pokemonRepository.getPokemonLocations()).thenReturn(
            ResultOf.Success(
                listOf(
                    PokemonLocation(1, 37.394919, 127.111138),
                    PokemonLocation(25, 37.350613, 127.173124),
                    PokemonLocation(25, 37.401250, 127.173124),
                    PokemonLocation(52, 37.401250, 127.110660),
                    PokemonLocation(52, 37.388999, 127.117476),
                    PokemonLocation(52, 37.388078, 127.121618)
                )
            )
        )

        viewModel.getPokemonLocations(52)
        assertThat(viewModel.pokemonLocationsLiveData.value?.size).isEqualTo(3)
    }


    @Test
    fun getPokemonLocationsFailTest() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(pokemonRepository.getPokemonLocations()).thenReturn(
            ResultOf.Error(Exception("error"))
        )
        Mockito.`when`(resourcesProvider.getString(R.string.error_retrieving_pokemon_location)).thenReturn("error")

        val job = launch {
            viewModel.errorFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo("error")
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.getPokemonLocations(1)
        job.join()
        job.cancel()
    }
}

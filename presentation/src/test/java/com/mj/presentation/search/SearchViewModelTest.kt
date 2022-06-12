package com.mj.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mj.pokemonapp.R
import com.mj.domain.model.PokemonName
import com.mj.domain.repository.PokemonRepository
import com.mj.domain.usecase.SearchPokemonUseCase
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
class SearchViewModelTest {

    @get:Rule
    var instantExceptionRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var pokemonRepository: PokemonRepository

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val searchPokemonUseCase = SearchPokemonUseCase(pokemonRepository)
        viewModel = SearchViewModel(searchPokemonUseCase, resourcesProvider)
    }

    @Test
    fun searchPokemonSuccessTest() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(pokemonRepository.getPokemonList()).thenReturn(
            ResultOf.Success(
                listOf(
                    PokemonName(10, "", "캐터피", "Caterpie"),
                    PokemonName(25, "", "피카츄", "Pikachu"),
                    PokemonName(30, "", "니드리나", "Nidorina"),
                    PokemonName(92, "", "고오스", "Gastly"),
                )
            )
        )

        var expectedAnswer = listOf(
            PokemonName(10, "Caterpie", "캐터피", "Caterpie"),
            PokemonName(25, "Pikachu", "피카츄", "Pikachu"),
        )

        viewModel.searchPokemon("pi")
        assertThat(viewModel.searchPokemonListLiveData.value).isEqualTo(expectedAnswer)


        expectedAnswer = listOf(
            PokemonName(92, "고오스", "고오스", "Gastly")
        )


        viewModel.searchPokemon("오")
        assertThat(viewModel.searchPokemonListLiveData.value).isEqualTo(expectedAnswer)
    }


    @Test
    fun searchPokemonFailTest() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(pokemonRepository.getPokemonList()).thenReturn(ResultOf.Error(Exception()))
        Mockito.`when`(resourcesProvider.getString(R.string.error_retrieving_pokemon_list)).thenReturn("error")

        val job = launch {
            viewModel.errorFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo("error")
                cancelAndConsumeRemainingEvents()
            }
        }

        viewModel.searchPokemon("1")
        job.join()
        job.cancel()
    }


}
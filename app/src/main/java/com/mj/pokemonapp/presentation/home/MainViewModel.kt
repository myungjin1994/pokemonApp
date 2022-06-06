package com.mj.pokemonapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.usecase.SearchPokemonUseCase
import com.mj.pokemonapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private var searchPokemonJob: Job? = null

    private val _pokemonSearchLiveData = MutableLiveData<List<PokemonName>>()
    val pokemonSearchLiveData: LiveData<List<PokemonName>> = _pokemonSearchLiveData


    fun searchPokemon(searchString: String) {
        searchPokemonJob?.cancel()
        searchPokemonJob = viewModelScope.launch {
            when (val result = searchPokemonUseCase(searchString)) {
                is Result.Success -> {
                    _pokemonSearchLiveData.value = result.data ?: listOf()
                }
                is Result.Error -> {

                }
            }
        }
    }


}
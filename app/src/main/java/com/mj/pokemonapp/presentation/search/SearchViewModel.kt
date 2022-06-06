package com.mj.pokemonapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.pokemonapp.domain.usecase.SearchPokemonUseCase
import com.mj.pokemonapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private var searchPokemonJob: Job? = null

    private val _searchStateLiveData = MutableLiveData<SearchState>(SearchState.Uninitialized)
    val searchStateLiveData: LiveData<SearchState> = _searchStateLiveData


    fun searchPokemon(searchString: String) {
        searchPokemonJob?.cancel()
        searchPokemonJob = viewModelScope.launch {
            _searchStateLiveData.value = SearchState.LoadingRead
            when (val result = searchPokemonUseCase(searchString)) {
                is Result.Success -> {
                    _searchStateLiveData.value = SearchState.SuccessReadPokemonList(result.data)
                }
                is Result.Error -> {
                    _searchStateLiveData.value = SearchState.Error(result.exception.toString())
                }
            }
        }
    }


}
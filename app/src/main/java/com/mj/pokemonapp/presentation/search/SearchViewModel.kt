package com.mj.pokemonapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.pokemonapp.R
import com.mj.pokemonapp.domain.model.PokemonName
import com.mj.pokemonapp.domain.usecase.SearchPokemonUseCase
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.resourceprovider.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private var searchPokemonJob: Job? = null

    private val _searchPokemonListLiveData = MutableLiveData<List<PokemonName>>()
    val searchPokemonListLiveData: LiveData<List<PokemonName>> = _searchPokemonListLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    private var prevSearchString = ""

    fun searchPokemon(searchString: String) {

        //직전 검색어와 동일한지 체크, 동일하면 검색기능 수행X
        if (prevSearchString == searchString) return
        prevSearchString = searchString

        // 기존에 검색이 진행중이면 해당 검색 cancel 후 검색
        searchPokemonJob?.cancel()
        searchPokemonJob = viewModelScope.launch {
            _loadingLiveData.value = true

            when (val result = searchPokemonUseCase(searchString)) {
                is Result.Success -> {
                    _searchPokemonListLiveData.value = result.data!!
                    _loadingLiveData.value = false
                }
                is Result.Error -> {
                    _errorFlow.emit(resourcesProvider.getString(R.string.error_retrieving_pokemon_list))
                    _loadingLiveData.value = false
                }
            }
        }
    }


}
package com.mj.pokemonapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.pokemonapp.R
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonLocation
import com.mj.pokemonapp.domain.usecase.GetPokemonDetailUseCase
import com.mj.pokemonapp.domain.usecase.GetPokemonLocationsUseCase
import com.mj.pokemonapp.util.Result
import com.mj.pokemonapp.util.resourceprovider.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getPokemonLocationsUseCase: GetPokemonLocationsUseCase,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _currentFragmentTag = MutableLiveData<String>(null)
    val currentFragmentTag: LiveData<String> = _currentFragmentTag

    private val _pokemonDetailLiveData = MutableLiveData<PokemonDetail>()
    val pokemonDetailLiveData: LiveData<PokemonDetail> = _pokemonDetailLiveData

    private val _pokemonLocationsLiveData = MutableLiveData<List<PokemonLocation>>()
    val pokemonLocationsLiveData: LiveData<List<PokemonLocation>> = _pokemonLocationsLiveData

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun setCurrentFragment(tag: String) {
        _currentFragmentTag.value = tag
    }

    fun getPokemonDetail(id: Int) {
        viewModelScope.launch {
            when (val result = getPokemonDetailUseCase(id)) {
                is Result.Success -> {
                    _pokemonDetailLiveData.value = result.data!!
                }
                is Result.Error -> {
                    _errorFlow.emit(resourcesProvider.getString(R.string.error_retrieving_pokemon_detail))
                }
            }
        }
    }

    fun getPokemonLocations(id: Int) {
        viewModelScope.launch {
            when (val result = getPokemonLocationsUseCase(id)) {
                is Result.Success -> {
                    _pokemonLocationsLiveData.value = result.data ?: listOf()
                }
                is Result.Error -> {
                    _errorFlow.emit(resourcesProvider.getString(R.string.error_retrieving_pokemon_location))
                }
            }
        }
    }

}
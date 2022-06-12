package com.mj.data.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mj.pokemonapp.R
import com.mj.domain.model.PokemonDetail
import com.mj.domain.model.PokemonLocation
import com.mj.domain.usecase.GetPokemonDetailUseCase
import com.mj.domain.usecase.GetPokemonLocationsUseCase
import com.mj.domain.utils.ResultOf
import com.mj.data.utils.resourceprovider.ResourcesProvider
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

    private val _pokemonDetailLoadingLiveData = MutableLiveData<Boolean>()
    val pokemonDetailLoadingLiveData: LiveData<Boolean> = _pokemonDetailLoadingLiveData

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
        // 한번 검색 결과가 나왔으면 다시 검색하지 않는다
        if(_pokemonDetailLiveData.value != null) return

        _pokemonDetailLoadingLiveData.value = true
        viewModelScope.launch {
            when (val result = getPokemonDetailUseCase(id)) {
                is ResultOf.Success -> {
                    _pokemonDetailLiveData.value = result.data!!
                    _pokemonDetailLoadingLiveData.value = false
                }
                is ResultOf.Error -> {
                    _errorFlow.emit(resourcesProvider.getString(R.string.error_retrieving_pokemon_detail))
                    _pokemonDetailLoadingLiveData.value = false
                }
            }
        }
    }

    fun getPokemonLocations(id: Int) {
        // 한번 서식지 검색 결과가 나왔으면 다시 검색하지 않는다
        if(_pokemonLocationsLiveData.value != null) return

        viewModelScope.launch {
            when (val result = getPokemonLocationsUseCase(id)) {
                is ResultOf.Success -> {
                    _pokemonLocationsLiveData.value = result.data!!
                }
                is ResultOf.Error -> {
                    _errorFlow.emit(resourcesProvider.getString(R.string.error_retrieving_pokemon_location))
                }
            }
        }
    }

}
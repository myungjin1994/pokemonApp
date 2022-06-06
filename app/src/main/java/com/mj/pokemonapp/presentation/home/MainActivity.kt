package com.mj.pokemonapp.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mj.pokemonapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.mj.pokemonapp.presentation.home.adapter.PokemonListAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observeData()
    }

    private fun initView() = with(binding) {
        pokemonListAdapter = getPokemonListAdapter()
        recyclerviewPokemonInfo.adapter = pokemonListAdapter
        setSearchEditText()
    }

    private fun observeData() {
        viewModel.pokemonSearchLiveData.observe(this) {
            pokemonListAdapter.submitList(it)
        }

    }


    private fun setSearchEditText() = with(binding) {
        //setSearchTextDeleteButton()
        edittextSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    viewModel.searchPokemon(text.toString().trim())
//                    imageviewDelete.isVisible = true
//                    recyclerviewBookInfo.isVisible = true
//                    groupSearch.isVisible = false
                } else { // 입력창이 배워 있을때
//                    recyclerviewBookInfo.isVisible = false
//                    imageviewDelete.isVisible = false
//                    groupSearch.isVisible = true
//                    groupError.isVisible = false
                }
            }
        }
    }

    private fun getPokemonListAdapter(): PokemonListAdapter = with(binding) {
        return@with PokemonListAdapter {

        }
    }


}
package com.mj.pokemonapp.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.ActivitySearchBinding
import com.mj.pokemonapp.presentation.search.adapter.PokemonListAdapter
import com.mj.pokemonapp.presentation.detail.DetailActivity

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
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
        viewModel.searchStateLiveData.observe(this) {
            when (it) {
                is SearchState.LoadingRead -> {
                    binding.progressSearch.isVisible = true
                }

                is SearchState.SuccessReadPokemonList -> {
                    pokemonListAdapter.submitList(it.pokemonList)
                    binding.progressSearch.isVisible = false
                }
                is SearchState.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressSearch.isVisible = false
                }
            }
        }
    }


    private fun setSearchEditText() = with(binding) {
        edittextSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isNotEmpty()) {
                    viewModel.searchPokemon(text.toString().trim())
                } else { // 입력창이 배워 있을때
                }
            }
        }
    }

    private fun getPokemonListAdapter(): PokemonListAdapter = with(binding) {
        return@with PokemonListAdapter {
            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra(getString(R.string.pokemonName), it)
            startActivity(intent)
        }
    }


}
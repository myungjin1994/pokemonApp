package com.mj.pokemonapp.presentation.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.ActivitySearchBinding
import com.mj.pokemonapp.presentation.search.adapter.PokemonListAdapter
import com.mj.pokemonapp.presentation.detail.DetailActivity
import kotlinx.coroutines.flow.collectLatest

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
        viewModel.loadingLiveData.observe(this) {
            binding.progressSearch.isVisible = it
        }

        viewModel.searchPokemonListLiveData.observe(this) {
            pokemonListAdapter.submitList(it)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorFlow.collectLatest {
                Toast.makeText(this@SearchActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setSearchEditText() = with(binding) {
        edittextSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null && text.isNotEmpty()) {
                viewModel.searchPokemon(text.toString().trim())
            }
        }
    }

    private fun getPokemonListAdapter(): PokemonListAdapter = with(binding) {
        return@with PokemonListAdapter {
            hideKeyboard(binding.edittextSearch)

            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra(getString(R.string.pokemonName), it)
            startActivity(intent)
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
package com.mj.pokemonapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.ActivityDetailBinding
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBackButton()
        observeData()
        getPokemonDetail()
    }

    private fun observeData() {
        viewModel.detailStateLiveData.observe(this) {
            when (it) {
                is DetailState.SuccessReadPokemonDetail -> {
                    setPokemonDetail(it.pokemonDetail)
                }
                is DetailState.SuccessReadPokemonLocations -> {
                    if (it.pokemonLocationList.isNotEmpty()) {
                        binding.buttonLocation.isEnabled = true
                    }
                }
            }
        }
    }

    private fun setBackButton() = with(binding) {
        imageviewBack.setOnClickListener {
            finish()
        }
    }

    private fun getPokemonDetail() {
        val pokemonName = intent.getParcelableExtra<PokemonName>(getString(R.string.pokemonName))
        setPokemonName(pokemonName?.nameKorean, pokemonName?.nameEnglish)
        pokemonName?.let {
            viewModel.getPokemonDetail(it.id)
            viewModel.getPokemonLocations(it.id)
        }
    }

    private fun setPokemonName(nameKorean: String?, nameEnglish: String?) = with(binding) {
        textviewPokemonNameKorean.text = nameKorean ?: ""
        textviewPokemonNameEnglish.text = nameEnglish ?: ""
    }

    private fun setPokemonDetail(pokemonDetail: PokemonDetail) = with(binding) {

        Glide.with(this@DetailActivity)
            .load(pokemonDetail.image)
            .placeholder(R.drawable.image_pokeball)
            .error(R.drawable.image_pokeball)
            .into(imageviewPokemon)

        textviewPokemonHeight.text = pokemonDetail.height.toString()
        textviewPokemonWeight.text = pokemonDetail.weight.toString()
    }
}
package com.mj.pokemonapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.FragmentDetailBinding
import com.mj.pokemonapp.domain.model.PokemonDetail
import com.mj.pokemonapp.domain.model.PokemonName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        observeData()
        getPokemonDetail()
    }


    private fun observeData() {

        viewModel.pokemonDetailLiveData.observe(viewLifecycleOwner) {
            setPokemonDetail(it)
        }

        viewModel.pokemonLocationsLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.buttonLocation.isEnabled = true
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorFlow.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setBackButton() = with(binding) {
        imageviewBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun getPokemonDetail() {
        val pokemonName = requireActivity().intent.getParcelableExtra<PokemonName>(getString(R.string.pokemonName))
        setPokemonName(pokemonName?.nameKorean, pokemonName?.nameEnglish)
        pokemonName?.let {
            viewModel.getPokemonDetail(it.id)
            viewModel.getPokemonLocations(it.id)
        }
    }

    private fun setPokemonName(nameKorean: String?, nameEnglish: String?) = with(binding) {
        textviewPokemonNameKorean.text = nameKorean ?: "unknown"
        textviewPokemonNameEnglish.text = nameEnglish ?: "unknown"
    }

    private fun setPokemonDetail(pokemonDetail: PokemonDetail) = with(binding) {

        Glide.with(requireContext())
            .load(pokemonDetail.image)
            .placeholder(R.drawable.image_pokeball)
            .error(R.drawable.image_pokeball)
            .into(imageviewPokemon)

        textviewPokemonHeight.text = pokemonDetail.height.toString()
        textviewPokemonWeight.text = pokemonDetail.weight.toString()

        buttonLocation.setOnClickListener {
            viewModel.setCurrentFragment(LocationFragment.TAG)
        }
    }

    companion object {
        const val TAG = "DetailFragment"
        fun newInstance() = DetailFragment()
    }


}
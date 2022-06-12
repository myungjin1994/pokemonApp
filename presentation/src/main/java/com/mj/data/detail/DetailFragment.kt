package com.mj.data.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.FragmentDetailBinding
import com.mj.domain.model.PokemonDetail
import com.mj.domain.model.PokemonLocation
import com.mj.domain.model.PokemonName
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

        val pokemonName = requireActivity().intent.getParcelableExtra<PokemonName>(getString(R.string.pokemonName))
        setPokemonName(pokemonName?.nameKorean, pokemonName?.nameEnglish)
        getPokemonDetail(pokemonName?.id ?: 0)
    }


    private fun observeData() {

        viewModel.pokemonDetailLoadingLiveData.observe(viewLifecycleOwner) {
            binding.progressDetail.isVisible = it
        }

        viewModel.pokemonDetailLiveData.observe(viewLifecycleOwner) {
            setPokemonDetail(it)
        }

        viewModel.pokemonLocationsLiveData.observe(viewLifecycleOwner) {
            setPokemonLocationButton(it)
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

    private fun getPokemonDetail(id: Int) {
        viewModel.getPokemonDetail(id)
        viewModel.getPokemonLocations(id)
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

        textviewPokemonHeight.text = getString(R.string.height_value, pokemonDetail.height.toString())
        textviewPokemonWeight.text = getString(R.string.weight_value, pokemonDetail.weight.toString())

    }

    private fun setPokemonLocationButton(pokemonLocationList: List<PokemonLocation>) = with(binding) {
        if (pokemonLocationList.isNotEmpty()) {
            buttonLocation.locationExists(true)
            buttonLocation.setOnClickListener {
                viewModel.setCurrentFragment(LocationFragment.TAG)
            }
        } else {
            buttonLocation.locationExists(false)
        }
    }

    companion object {
        const val TAG = "DetailFragment"
        fun newInstance() = DetailFragment()
    }
}
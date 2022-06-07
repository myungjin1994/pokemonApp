package com.mj.pokemonapp.presentation.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.FragmentLocationBinding
import com.mj.pokemonapp.domain.model.PokemonLocation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: DetailViewModel by activityViewModels()
    private lateinit var binding: FragmentLocationBinding
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    private fun observeData() {
        viewModel.pokemonLocationsLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                setPokemonLocationsAndMoveCamera(it, mMap)
            }
        }
    }


    private fun setBackButton() = with(binding) {
        imageviewBack.setOnClickListener {
            viewModel.setCurrentFragment(DetailFragment.TAG)
        }
    }

    private fun setPokemonLocationsAndMoveCamera(pokemonLocations: List<PokemonLocation>, googleMap: GoogleMap) {
        val bounds = LatLngBounds.builder()

        pokemonLocations.forEach { pokemonLocation ->
            val pokemonLocationLatLng = LatLng(pokemonLocation.lat, pokemonLocation.lng)
            googleMap.addMarker(
                MarkerOptions()
                    .position(pokemonLocationLatLng)
                    .title(pokemonLocationLatLng.toString())
            )
            bounds.include(pokemonLocationLatLng)

        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 200))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.setCurrentFragment(DetailFragment.TAG)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }


    companion object {
        const val TAG = "LocationFragment"
        fun newInstance() = LocationFragment()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        observeData()
    }

}
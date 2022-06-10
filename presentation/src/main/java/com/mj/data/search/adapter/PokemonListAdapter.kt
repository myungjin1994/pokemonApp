package com.mj.data.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mj.data.utils.EspressoIdlingResource
import com.mj.pokemonapp.databinding.ViewholderPokemonNameBinding
import com.mj.domain.model.PokemonName

class PokemonListAdapter(
    private val itemClick: (PokemonName) -> Unit
) : ListAdapter<PokemonName, PokemonListAdapter.PokemonViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.PokemonViewHolder {
        val binding = ViewholderPokemonNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding) {
            itemClick(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class PokemonViewHolder(private val binding: ViewholderPokemonNameBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.constraintlayoutPokemon.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(pokemonName: PokemonName) = with(binding) {
            textviewPokemonName.text = pokemonName.searchName
        }

    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<PokemonName>() {

            override fun areItemsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
                return oldItem == newItem
            }
        }
    }
}
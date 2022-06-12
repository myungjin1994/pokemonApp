package com.mj.data.detail.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.mj.pokemonapp.R
import com.mj.pokemonapp.databinding.ButtonPokemonLocationBinding

class PokemonLocationButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private var binding: ButtonPokemonLocationBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.button_pokemon_location, this, true)

    init {
        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PokemonLocationButton)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        binding.background.setBackgroundResource(R.color.transparent)
        typedArray.recycle()
    }

    fun locationExists(enable: Boolean) = with(binding) {
        progressLocation.isVisible = false
        when(enable) {
            true -> {
                textviewTitle.text = context.getString(R.string.location)
                imageviewArrow.isVisible = true
            }
            false -> {
                textviewTitle.text = context.getString(R.string.location_none)
                imageviewArrow.isVisible = false
            }
        }
    }
}
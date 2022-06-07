package com.mj.pokemonapp.util.resourceprovider

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg  formArgs: Any): String

}
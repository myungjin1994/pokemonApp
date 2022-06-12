package com.mj.presentation.utils.resourceprovider

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg  formArgs: Any): String

}
package com.mj.presentation.utils.resourceprovider

import android.content.Context
import androidx.annotation.StringRes

class DefaultResourcesProvider(
    private val context: Context
): ResourcesProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getString(@StringRes resId: Int, vararg formArgs: Any): String = context.getString(resId, *formArgs)


}
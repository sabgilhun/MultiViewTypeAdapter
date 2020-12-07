package com.sabgil.mutiviewtype.scope

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.ViewTypeMapStore

class MultiViewTypeScope(private val context: Context) {
    private val viewTypeScopes = mutableListOf<ViewTypeScope<out BaseItem, out ViewDataBinding>>()

    fun addType(type: ViewTypeScope<out BaseItem, out ViewDataBinding>) {
        viewTypeScopes.add(type)
    }

    fun viewTypeMapStoreBuild() = ViewTypeMapStore.Builder(context, viewTypeScopes).build()
}
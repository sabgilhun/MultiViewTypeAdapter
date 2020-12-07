package com.sabgil.mutiviewtype

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.sabgil.mutiviewtype.invoker.OnBindInvoker
import com.sabgil.mutiviewtype.invoker.OnCreateInvoker
import com.sabgil.mutiviewtype.scope.ViewTypeScope
import java.util.*

class ViewTypeMapStore(
    private val layoutMap: Map<Class<out BaseItem>, Int>,
    private val onBindInvokerMap: Map<Class<out BaseItem>, OnBindInvoker>,
    private val onCreateInvokerMap: Map<Int, OnCreateInvoker>
) {

    fun getLayoutId(itemTypeClass: Class<out BaseItem>) =
        requireNotNull(layoutMap[itemTypeClass])

    fun getOnBindInvoker(itemTypeClass: Class<out BaseItem>) =
        requireNotNull(onBindInvokerMap[itemTypeClass])

    fun getOnCreateInvoker(@LayoutRes layoutId: Int) =
        requireNotNull(onCreateInvokerMap[layoutId])

    class Builder(
        private val context: Context,
        private val types: List<ViewTypeScope<out BaseItem, out ViewDataBinding>>
    ) {
        private val regex = "([a-z])([A-Z]+)".toRegex()
        private val layoutIds = types.map { it.bindingClass.toLayoutId() }

        fun build() = ViewTypeMapStore(
            buildLayoutIdMap(),
            buildOnBindInvokerMap(),
            buildOnCreateInvokerMap()
        )

        private fun buildLayoutIdMap() = hashMapOf(
            *(layoutIds.zip(types) { id, type -> type.itemClass to id }.toTypedArray())
        )

        private fun buildOnBindInvokerMap() = hashMapOf(
            *(types.map { it.itemClass to it.onBindInvoker }.toTypedArray())
        )

        private fun buildOnCreateInvokerMap() = hashMapOf(
            *(layoutIds.zip(types) { id, type -> id to type.onCreateInvoker }.toTypedArray())
        )

        private fun Class<out ViewDataBinding>.toLayoutId(): Int {
            val layoutIdStr = simpleName
                .removeSuffix(bindingClassSuffix)
                .replace(regex, replacement)
                .toLowerCase(Locale.getDefault())

            return context.resources.getIdentifier(layoutIdStr, resourceType, context.packageName)
        }

        companion object {
            private const val replacement = "$1_$2"
            private const val bindingClassSuffix = "Binding"
            private const val resourceType = "layout"
        }
    }
}
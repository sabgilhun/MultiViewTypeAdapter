package com.sabgil.mutiviewtype

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.sabgil.mutiviewtype.scope.MultiViewTypeScope
import com.sabgil.mutiviewtype.scope.ViewTypeScope

inline fun Context.viewTypeMapStore(block: MultiViewTypeScope.() -> Unit): ViewTypeMapStore {
    return MultiViewTypeScope(this).apply(block).viewTypeMapStoreBuild()
}

inline fun Context.multiViewTypeAdapter(block: MultiViewTypeScope.() -> Unit): MultiViewTypeAdapter {
    val viewTypeMapStore = MultiViewTypeScope(this).apply(block).viewTypeMapStoreBuild()
    return object : MultiViewTypeAdapter() {
        override val viewTypeMapStore: ViewTypeMapStore
            get() = viewTypeMapStore
    }
}

inline fun <reified I : BaseItem, reified B : ViewDataBinding> Context.singleViewTypeAdapter(
    block: ViewTypeScope<I, B>.() -> Unit
): MultiViewTypeAdapter {
    val viewTypeMapStore = MultiViewTypeScope(this).apply {
        addType(ViewTypeScope(I::class.java, B::class.java).apply(block))
    }.viewTypeMapStoreBuild()

    return object : MultiViewTypeAdapter() {
        override val viewTypeMapStore: ViewTypeMapStore
            get() = viewTypeMapStore
    }
}

inline fun <reified I : BaseItem, reified B : ViewDataBinding> MultiViewTypeScope.type(
    block: ViewTypeScope<I, B>.() -> Unit = {}
) {
    addType(ViewTypeScope(I::class.java, B::class.java).apply(block))
}
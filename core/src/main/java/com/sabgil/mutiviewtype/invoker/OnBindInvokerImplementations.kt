package com.sabgil.mutiviewtype.invoker

import androidx.databinding.ViewDataBinding
import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.BindingViewHolder

class NonParamOnBindInvoker(
    private val _onBind: () -> Unit
) : OnBindInvoker {
    override fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int) {
        _onBind()
    }
}

class ItemParamOnBindInvoker<I : BaseItem>(
    onBind: (I) -> Unit
) : OnBindInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onBind = onBind as (BaseItem) -> Unit

    override fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int) {
        _onBind(item)
    }
}

class ItemAndBindingParamOnBindInvoker<I : BaseItem, B : ViewDataBinding>(
    onBind: (I, B) -> Unit
) : OnBindInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onBind = onBind as (BaseItem, ViewDataBinding) -> Unit

    override fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int) {
        _onBind(item, viewHolder.binding)
    }
}

class ItemAndBindingAndPositionParamOnBindInvoker<I : BaseItem, B : ViewDataBinding>(
    onBind: (I, B, Int) -> Unit
) : OnBindInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onBind = onBind as (BaseItem, BindingViewHolder, Int) -> Unit

    override fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int) {
        _onBind(item, viewHolder, position)
    }
}
package com.sabgil.mutiviewtype.invoker

import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.BindingViewHolder

interface OnBindInvoker {

    fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int)

    companion object EmptyInvoker : OnBindInvoker {
        override fun invokeOnBind(item: BaseItem, viewHolder: BindingViewHolder, position: Int) {
            // Empty operation
        }
    }
}
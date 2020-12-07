package com.sabgil.mutiviewtype.invoker

import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.BindingViewHolder

interface OnCreateInvoker {

    fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>)

    companion object EmptyInvoker : OnCreateInvoker {
        override fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>) {
            // Empty operation
        }
    }
}
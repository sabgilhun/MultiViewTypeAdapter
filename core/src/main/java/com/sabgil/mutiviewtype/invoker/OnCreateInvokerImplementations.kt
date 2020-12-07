package com.sabgil.mutiviewtype.invoker

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.BindingViewHolder

class NonParamOnCreateInvoker(
    private val _onCreate: () -> Unit
) : OnCreateInvoker {
    override fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>) {
        _onCreate()
    }
}

class BindingParamOnCreateInvoker<B : ViewDataBinding>(
    onCreate: (B) -> Unit
) : OnCreateInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onCreate = onCreate as (ViewDataBinding) -> Unit

    override fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>) {
        _onCreate(viewHolder.binding)
    }
}

class BindingAndHolderParamOnCreateInvoker<B : ViewDataBinding>(
    onCreate: (B, RecyclerView.ViewHolder) -> Unit
) : OnCreateInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onCreate = onCreate as (ViewDataBinding, RecyclerView.ViewHolder) -> Unit

    override fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>) {
        _onCreate(viewHolder.binding, viewHolder)
    }
}

class BindingAndHolderAndItemSupplierParamOnCreateInvoker<I : BaseItem, B : ViewDataBinding>(
    onCreate: (B, RecyclerView.ViewHolder, () -> I?) -> Unit
) : OnCreateInvoker {
    @Suppress("UNCHECKED_CAST")
    private val _onCreate =
        onCreate as (ViewDataBinding, RecyclerView.ViewHolder, () -> BaseItem?) -> Unit

    override fun invokeOnCreate(viewHolder: BindingViewHolder, items: List<BaseItem>) {
        val holder = viewHolder as RecyclerView.ViewHolder
        _onCreate(viewHolder.binding, viewHolder) {
            val position = holder.adapterPosition
            if (position == NO_POSITION) {
                null
            } else {
                items[position]
            }
        }
    }
}
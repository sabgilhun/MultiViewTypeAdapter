package com.sabgil.mutiviewtype.scope

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sabgil.mutiviewtype.BaseItem
import com.sabgil.mutiviewtype.invoker.*

class ViewTypeScope<I : BaseItem, B : ViewDataBinding>(
    val itemClass: Class<I>,
    val bindingClass: Class<B>
) {
    private var _onBindInvoker: OnBindInvoker = OnBindInvoker.EmptyInvoker
    private var _onCreateInvoker: OnCreateInvoker = OnCreateInvoker.EmptyInvoker

    val onBindInvoker get() = _onBindInvoker
    val onCreateInvoker get() = _onCreateInvoker

    fun onBind(onBind: () -> Unit) {
        _onBindInvoker = NonParamOnBindInvoker(onBind)
    }

    fun onBind(onBind: (I) -> Unit) {
        _onBindInvoker = ItemParamOnBindInvoker(onBind)
    }

    fun onBind(onBind: (I, B) -> Unit) {
        _onBindInvoker = ItemAndBindingParamOnBindInvoker(onBind)
    }

    fun onBind(onBind: (I, B, Int) -> Unit) {
        _onBindInvoker = ItemAndBindingAndPositionParamOnBindInvoker(onBind)
    }

    fun onCreate(onCreate: () -> Unit) {
        _onCreateInvoker = NonParamOnCreateInvoker(onCreate)
    }

    fun onCreate(onCreate: (B) -> Unit) {
        _onCreateInvoker = BindingParamOnCreateInvoker(onCreate)
    }

    fun onCreate(onCreate: (B, RecyclerView.ViewHolder) -> Unit) {
        _onCreateInvoker = BindingAndHolderParamOnCreateInvoker(onCreate)
    }

    fun onCreate(onCreate: (B, RecyclerView.ViewHolder, () -> I?) -> Unit) {
        _onCreateInvoker = BindingAndHolderAndItemSupplierParamOnCreateInvoker(onCreate)
    }
}
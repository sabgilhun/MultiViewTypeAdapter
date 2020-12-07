package com.sabgil.mutiviewtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class MultiViewTypeAdapter : RecyclerView.Adapter<BindingViewHolder>() {

    protected abstract val viewTypeMapStore: ViewTypeMapStore

    private var _items: List<BaseItem> by Delegates.observable(mutableListOf())
    { _, old, new -> dispatchDiff(old, new) }
    val items: List<BaseItem> get() = _items

    open fun update(items: List<BaseItem>) {
        this._items = items.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = DataBindingUtil
            .inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        val viewHolder = BindingViewHolder(binding)
        viewTypeMapStore.getOnCreateInvoker(viewType).invokeOnCreate(viewHolder, _items)
        return viewHolder
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = items[position]
        viewTypeMapStore.getOnBindInvoker(item::class.java).invokeOnBind(item, holder, position)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) =
        viewTypeMapStore.getLayoutId(items[position]::class.java)

    private fun dispatchDiff(
        old: List<BaseItem>,
        new: List<BaseItem>
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size
        })

        diff.dispatchUpdatesTo(this)
    }
}
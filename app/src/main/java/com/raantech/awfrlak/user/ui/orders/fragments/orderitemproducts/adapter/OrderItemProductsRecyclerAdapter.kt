package com.raantech.awfrlak.user.ui.orders.fragments.orderitemproducts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raantech.awfrlak.databinding.RowOrderItemProductsBinding
import com.raantech.awfrlak.user.data.models.orders.entity.OrderProduct
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.adapters.BaseViewHolder
import com.raantech.awfrlak.user.utils.extensions.setSlideAnimation

class OrderItemProductsRecyclerAdapter constructor(
    context: Context
) : BaseBindingRecyclerViewAdapter<OrderProduct>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowOrderItemProductsBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setSlideAnimation(position)
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ViewHolder(private val binding: RowOrderItemProductsBinding) :
        BaseViewHolder<OrderProduct>(binding.root) {

        override fun bind(item: OrderProduct) {
            binding.item = item
            binding.linRoot.setOnClickListener {
                itemClickListener?.onItemClick(it,bindingAdapterPosition,item)
            }
        }
    }
}
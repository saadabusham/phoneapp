package com.raantech.awfrlak.user.ui.purchase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raantech.awfrlak.user.data.models.Purchase
import com.raantech.awfrlak.databinding.RowPurchaseBinding
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.adapters.BaseViewHolder
import com.raantech.awfrlak.user.utils.extensions.setSlideAnimation

class PurchasesRecyclerAdapter constructor(
    context: Context
) : BaseBindingRecyclerViewAdapter<Purchase>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowPurchaseBinding.inflate(
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

    inner class ViewHolder(private val binding: RowPurchaseBinding) :
        BaseViewHolder<Purchase>(binding.root) {

        override fun bind(item: Purchase) {
            binding.item = item
        }
    }
}
package com.technzone.phoneapp.ui.purchase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technzone.phoneapp.data.models.Purchase
import com.technzone.phoneapp.databinding.RowPurchaseBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.adapters.BaseViewHolder
import com.technzone.phoneapp.utils.extensions.setSlideAnimation

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
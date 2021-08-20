package com.technzone.phoneapp.ui.cart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technzone.phoneapp.data.models.accessories.Accessory
import com.technzone.phoneapp.databinding.RowCartBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.adapters.BaseViewHolder
import com.technzone.phoneapp.utils.extensions.setSlideAnimation

class CartRecyclerAdapter(
    context: Context
) : BaseBindingRecyclerViewAdapter<Accessory>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowCartBinding.inflate(
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

    inner class ViewHolder(private val binding: RowCartBinding) :
        BaseViewHolder<Accessory>(binding.root) {

        override fun bind(item: Accessory) {
            binding.item = item
            binding.count = item.count
            binding.imgPlus.setOnClickListener {
                binding.count = binding.count?.plus(1)
                item.count = binding.count
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
            binding.imgMinus.setOnClickListener {
                if (binding.count ?: 0 > 1)
                    binding.count = binding.count?.minus(1)
                item.count = binding.count
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
            binding.cvDelete.setOnClickListener {
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
        }
    }

}
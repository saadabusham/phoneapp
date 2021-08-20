package com.technzone.phoneapp.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technzone.phoneapp.data.models.City
import com.technzone.phoneapp.databinding.RowCityBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.adapters.BaseViewHolder
import com.technzone.phoneapp.utils.extensions.setSlideAnimation

class CityRecyclerAdapter constructor(
        context: Context,
        private val singleSelection: Boolean
) : BaseBindingRecyclerViewAdapter<City>(context) {
    private var selectedPosition: Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
                RowCityBinding.inflate(
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

    inner class ViewHolder(private val binding: RowCityBinding) :
            BaseViewHolder<City>(binding.root) {

        override fun bind(item: City) {
            binding.city = item
            binding.root.setOnClickListener {
                if (singleSelection) {
                    items.withIndex().forEach {
                        it.value.selected = false
                        notifyItemChanged(it.index)
                    }
                }
                item.selected = !item.selected
                notifyItemChanged(adapterPosition)
                itemClickListener?.onItemClick(it,adapterPosition,item)
            }
        }
    }
}
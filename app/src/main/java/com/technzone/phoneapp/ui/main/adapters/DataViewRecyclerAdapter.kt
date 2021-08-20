package com.technzone.phoneapp.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technzone.phoneapp.data.models.DataView
import com.technzone.phoneapp.databinding.RowDataViewBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.adapters.BaseViewHolder

class DataViewRecyclerAdapter(
        context: Context
) : BaseBindingRecyclerViewAdapter<DataView>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
                RowDataViewBinding.inflate(
                        LayoutInflater.from(context), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ViewHolder(private val binding: RowDataViewBinding) :
            BaseViewHolder<DataView>(binding.root) {

        override fun bind(item: DataView) {
            binding.item = item
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
        }
    }
}
package com.raantech.awfrlak.ui.main.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raantech.awfrlak.data.models.home.Service
import com.raantech.awfrlak.databinding.RowServiceGridBinding
import com.raantech.awfrlak.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.ui.base.adapters.BaseViewHolder
import com.raantech.awfrlak.utils.extensions.setPopUpAnimation

class ServicesGridRecyclerAdapter(
    context: Context
) : BaseBindingRecyclerViewAdapter<Service>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowServiceGridBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setPopUpAnimation(position)
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ViewHolder(private val binding: RowServiceGridBinding) :
        BaseViewHolder<Service>(binding.root) {

        override fun bind(item: Service) {
            binding.item = item
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(it, adapterPosition, item)
            }
        }
    }
}
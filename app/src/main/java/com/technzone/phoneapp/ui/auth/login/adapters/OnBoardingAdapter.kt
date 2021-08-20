package com.technzone.phoneapp.ui.auth.login.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technzone.phoneapp.data.models.auth.onboarding.OnBoardingItem
import com.technzone.phoneapp.databinding.RowOnBoardingBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.adapters.BaseViewHolder

class OnBoardingAdapter(
    context: Context
) :
    BaseBindingRecyclerViewAdapter<OnBoardingItem>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowOnBoardingBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ViewHolder(private val binding: RowOnBoardingBinding) :
        BaseViewHolder<OnBoardingItem>(binding.root) {

        override fun bind(item: OnBoardingItem) {
            binding.data = item
        }
    }


}
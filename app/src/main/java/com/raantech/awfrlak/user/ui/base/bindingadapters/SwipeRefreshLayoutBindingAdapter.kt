package com.raantech.awfrlak.user.ui.base.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("srlRefreshing")
fun SwipeRefreshLayout?.setRefreshing(isRefreshing:Boolean) {
    this?.isRefreshing = isRefreshing
}
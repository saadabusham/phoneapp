package com.raantech.awfrlak.user.ui.orders.fragments.orderitemproducts.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import com.raantech.awfrlak.R
import com.raantech.awfrlak.databinding.FragmentOrdersItemProductsBinding
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.user.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.user.ui.orders.fragments.orderitemproducts.adapter.OrderItemProductsRecyclerAdapter
import com.raantech.awfrlak.user.ui.orders.viewmodels.OrdersViewModel
import com.raantech.awfrlak.user.utils.extensions.gone
import com.raantech.awfrlak.user.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class OrderItemProductsFragment : BaseBindingFragment<FragmentOrdersItemProductsBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_orders_item_products

    private val viewModel: OrdersViewModel by activityViewModels()
    lateinit var adapter: OrderItemProductsRecyclerAdapter

    override fun onViewVisible() {
        super.onViewVisible()
        addToolbar(
            hasToolbar = true,
            toolbarView = toolbar,
            hasBackButton = true,
            showBackArrow = true,
            hasTitle = true,
            titleString = "#${viewModel.orderIdToView}"
        )
        init()
    }


    private fun init() {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter = OrderItemProductsRecyclerAdapter(requireContext())
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView.setOnItemClickListener(this)
        viewModel.orderItemToView?.prodcuts?.let {
            adapter.submitItems(it)
        }
        hideShowNoData()
    }

    private fun hideShowNoData() {
        if (adapter.itemCount == 0) {
            binding?.recyclerView?.gone()
            binding?.layoutNoData?.linearNoData?.visible()
        } else {
            binding?.layoutNoData?.linearNoData?.gone()
            binding?.recyclerView?.visible()
        }
    }

    override fun onItemClick(view: View?, position: Int, item: Any) {
        when (item) {

        }
    }

}
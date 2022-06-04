package com.raantech.awfrlak.user.ui.orders.fragments.orderitems.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import com.raantech.awfrlak.R
import com.raantech.awfrlak.databinding.FragmentOrdersItemsBinding
import com.raantech.awfrlak.user.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.user.data.common.CustomObserverResponse
import com.raantech.awfrlak.user.data.models.orders.entity.Order
import com.raantech.awfrlak.user.data.models.orders.entity.OrdersItem
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.user.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.user.ui.orders.fragments.orderitems.adapter.OrderItemsRecyclerAdapter
import com.raantech.awfrlak.user.ui.orders.viewmodels.OrdersViewModel
import com.raantech.awfrlak.user.utils.extensions.gone
import com.raantech.awfrlak.user.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class OrderItemsFragment : BaseBindingFragment<FragmentOrdersItemsBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_orders_items

    private val viewModel: OrdersViewModel by activityViewModels()
    lateinit var adapter: OrderItemsRecyclerAdapter

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
        adapter = OrderItemsRecyclerAdapter(requireContext())
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView.setOnItemClickListener(this)
        loadData()
    }

    private fun loadData() {
        viewModel.orderIdToView?.let {
            viewModel.getOrderDetails(it).observe(this, ordersObserver())
        }
    }

    private fun ordersObserver(): CustomObserverResponse<Order> {
        return CustomObserverResponse(
            requireActivity(),
            object : CustomObserverResponse.APICallBack<Order> {

                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: Order?
                ) {
                    data?.let {
                        it.orderItems?.let { adapter.submitItems(it) }
                        hideShowNoData()
                    }
                }
            }
        )
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
            is OrdersItem -> {
                viewModel.orderItemToView = item
                navigationController.navigate(R.id.action_orderItemsFragment_to_orderItemProductsFragment)
            }
        }
    }

}
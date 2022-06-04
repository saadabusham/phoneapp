package com.raantech.awfrlak.user.ui.orders.fragments.orders.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.paginate.Paginate
import com.raantech.awfrlak.R
import com.raantech.awfrlak.databinding.FragmentOrdersBinding
import com.raantech.awfrlak.user.data.api.response.GeneralError
import com.raantech.awfrlak.user.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.user.data.common.CustomObserverResponse
import com.raantech.awfrlak.user.data.models.orders.entity.Order
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.user.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.user.ui.orders.fragments.orders.adapter.OrdersRecyclerAdapter
import com.raantech.awfrlak.user.ui.orders.viewmodels.OrdersViewModel
import com.raantech.awfrlak.user.utils.extensions.gone
import com.raantech.awfrlak.user.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class OrdersFragment : BaseBindingFragment<FragmentOrdersBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_orders

    private val viewModel: OrdersViewModel by activityViewModels()
    lateinit var adapter: OrdersRecyclerAdapter

    private val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isFinished = false

    override fun onViewVisible() {
        super.onViewVisible()
        addToolbar(
            hasToolbar = true,
            toolbarView = toolbar,
            hasBackButton = true,
            showBackArrow = true,
            hasTitle = true,
            titleString = resources.getString(R.string.menu_my_purchases)
        )
        init()
    }


    private fun init() {
        setUpListeners()
        setUpAdapter()
        loadData()
    }

    private fun setUpListeners() {

    }

    private fun setUpAdapter() {
        adapter = OrdersRecyclerAdapter(requireContext())
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView.setOnItemClickListener(this)
        Paginate.with(binding?.recyclerView, object : Paginate.Callbacks {
            override fun onLoadMore() {
                if (loading.value == false && adapter.itemCount > 0 && !isFinished) {
                    loadData()
                }
            }

            override fun isLoading(): Boolean {
                return loading.value ?: false
            }

            override fun hasLoadedAllItems(): Boolean {
                return isFinished
            }

        })
            .setLoadingTriggerThreshold(1)
            .addLoadingListItem(false)
            .build()
    }

    private fun loadData() {
        viewModel.getOrders(adapter.itemCount).observe(this, ordersObserver())
    }

    private fun ordersObserver(): CustomObserverResponse<List<Order>> {
        return CustomObserverResponse(
            requireActivity(),
            object : CustomObserverResponse.APICallBack<List<Order>> {

                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: List<Order>?
                ) {
                    data?.let {
                        adapter.submitItems(it)
                    }
                    if (data.isNullOrEmpty())
                        isFinished = true
                    loading.value = false
                }

                override fun onError(
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    message: String,
                    errors: List<GeneralError>?
                ) {
                    super.onError(subErrorCode, message, errors)
                    loading.value = false
                    hideShowNoData()
                }

                override fun onLoading() {
                    loading.value = true
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
            is Order -> {
                viewModel.orderIdToView = item.orderNumber
                navigationController.navigate(R.id.action_ordersFragment_to_orderItemsFragment)
            }
        }
    }

}
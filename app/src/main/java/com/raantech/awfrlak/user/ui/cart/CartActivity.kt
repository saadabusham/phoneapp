package com.raantech.awfrlak.user.ui.cart

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.raantech.awfrlak.R
import com.raantech.awfrlak.user.data.models.Price
import com.raantech.awfrlak.user.data.models.home.AccessoriesItem
import com.raantech.awfrlak.user.data.models.home.MobilesItem
import com.raantech.awfrlak.databinding.ActivityCartBinding
import com.raantech.awfrlak.user.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.user.data.common.CustomObserverResponse
import com.raantech.awfrlak.user.data.enums.PaymentTypeEnum
import com.raantech.awfrlak.user.data.models.configuration.ConfigurationWrapperResponse
import com.raantech.awfrlak.user.ui.base.activity.BaseBindingActivity
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.user.ui.cart.adapters.CartRecyclerAdapter
import com.raantech.awfrlak.user.ui.cart.viewmodels.CartViewModel
import com.raantech.awfrlak.user.ui.payment.activity.PaymentActivity
import com.raantech.awfrlak.user.utils.extensions.gone
import com.raantech.awfrlak.user.utils.extensions.round
import com.raantech.awfrlak.user.utils.extensions.visible
import com.raantech.awfrlak.user.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class CartActivity : BaseBindingActivity<ActivityCartBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    private val viewModel: CartViewModel by viewModels()
    lateinit var cartRecyclerAdapter: CartRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            layoutResID = R.layout.activity_cart,
            hasToolbar = true,
            toolbarView = toolbar,
            hasTitle = true,
            title = R.string.cart,
            hasBackButton = true,
            showBackArrow = true
        )
        setUpBinding()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners() {
        binding?.btnPay?.setOnClickListener {
            viewModel.getOrderRequest().observe(this) {
                it.data?.let { it1 -> viewModel.createOrder(it1).observe(this, createOrderResultObserver()) }
            }
        }
    }

    private fun setUpBinding() {
        binding?.viewModel = viewModel
    }

    private fun setUpRecyclerView() {
        cartRecyclerAdapter = CartRecyclerAdapter(this)
        binding?.recyclerView?.adapter = cartRecyclerAdapter
        binding?.recyclerView?.setOnItemClickListener(this)
        loadData()
    }

    private fun loadData() {
        viewModel.getCarts(
        ).observe(this, {
            cartRecyclerAdapter.submitItems(it)
            calculattion()
            hideShowNoData()
        })
    }

    private fun calculattion() {
        binding?.count = cartRecyclerAdapter.itemCount
        var subtotal = 0.0
        cartRecyclerAdapter.items.forEach {
            if (it is MobilesItem)
                subtotal += it.price?.amount?.toDouble()?.times(it.count ?: 1) ?: 0.0
            else if (it is AccessoriesItem)
                subtotal += it.price?.amount?.toDouble()?.times(it.count ?: 1) ?: 0.0
        }
        viewModel.subTotal.postValue(
            Price(
                amount = subtotal.toString(),
                formatted = "$subtotal ${resources.getString(R.string.sar)}"
            )
        )
        viewModel.TAX_CONST?.times(subtotal)?.let {
            viewModel.tax.postValue(
                Price(
                    amount = it.toString(),
                    formatted = "${it.round(2)} ${resources.getString(R.string.sar)}"
                )
            )
            viewModel.total.postValue(
                Price(
                    amount = (subtotal + it).toString(),
                    formatted = "${(subtotal + it).round(2)} ${resources.getString(R.string.sar)}"
                )
            )
        }
    }

    private fun hideShowNoData() {
        if (cartRecyclerAdapter.itemCount == 0) {
            binding?.recyclerView?.gone()
            binding?.layoutNoData?.linearNoData?.visible()
            binding?.btnPay?.gone()
        } else {
            binding?.layoutNoData?.linearNoData?.gone()
            binding?.recyclerView?.visible()
            binding?.btnPay?.visible()
        }
    }

    override fun onItemClick(view: View?, position: Int, item: Any) {
        when (item) {
            is AccessoriesItem -> {
                if (view?.id == R.id.imgPlus || view?.id == R.id.imgMinus) {
                    viewModel.updateAccessoryCartItem(item)
                    calculattion()
                } else if (view?.id == R.id.cvDelete) {
                    item.id?.let {
                        viewModel.deleteAccessoryCart(it)
                        cartRecyclerAdapter.items.remove(item)
                        cartRecyclerAdapter.notifyItemRemoved(position)
                        calculattion()
                        hideShowNoData()
                    }
                }
            }
            is MobilesItem -> {
                if (view?.id == R.id.imgPlus || view?.id == R.id.imgMinus) {
                    viewModel.updateMobileCartItem(item)
                    calculattion()
                } else if (view?.id == R.id.cvDelete) {
                    item.id?.let {
                        viewModel.deleteMobileCart(it)
                        cartRecyclerAdapter.items.remove(item)
                        cartRecyclerAdapter.notifyItemRemoved(position)
                        calculattion()
                        hideShowNoData()
                    }
                }
            }
        }
    }

    private fun createOrderResultObserver(): CustomObserverResponse<String> {
        return CustomObserverResponse(
            this,
            object : CustomObserverResponse.APICallBack<String> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: String?
                ) {
                    if (viewModel.paymentType.value == PaymentTypeEnum.CASH_ON_DELIVERY) {
                        PayedSuccessActivity.start(this@CartActivity)
                    } else {
                        data?.let {
                            PaymentActivity.start(
                                context = this@CartActivity,
                                paymentUrl = it,
                                resultLauncher = resultLauncher
                            )
                        }
                    }
                }
            })
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == PaymentActivity.RESULT_SUCCESS) {
                PayedSuccessActivity.start(this@CartActivity)
            }else if (result.resultCode == PaymentActivity.RESULT_FAILED) {

            }
        }

    companion object {
        fun start(
            context: Context?
        ) {
            val intent = Intent(context, CartActivity::class.java)
            context?.startActivity(intent)
        }
    }

}
package com.raantech.awfrlak.user.ui.store.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.paginate.Paginate
import com.raantech.awfrlak.R
import com.raantech.awfrlak.user.data.api.response.GeneralError
import com.raantech.awfrlak.user.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.user.data.common.CustomObserverResponse
import com.raantech.awfrlak.user.data.models.home.MobilesItem
import com.raantech.awfrlak.databinding.LayoutPhonesGridBinding
import com.raantech.awfrlak.user.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.user.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.user.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.user.ui.main.home.adapters.PhonesGridRecyclerAdapter
import com.raantech.awfrlak.user.ui.main.viewmodels.GeneralViewModel
import com.raantech.awfrlak.user.ui.mobile.MobileDetailsActivity
import com.raantech.awfrlak.user.utils.extensions.gone
import com.raantech.awfrlak.user.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobilesFragment : BaseBindingFragment<LayoutPhonesGridBinding>(),
        BaseBindingRecyclerViewAdapter.OnItemClickListener {

    private val viewModel: GeneralViewModel by activityViewModels()

    private val loadingMobiles: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isMobilesFinished = false
    lateinit var phonesGridRecyclerAdapter: PhonesGridRecyclerAdapter
    override fun getLayoutId(): Int {
        return R.layout.layout_phones_grid
    }


    override fun onViewVisible() {
        super.onViewVisible()
        binding?.linearPhonesGrid?.visible()
        loadingMobilesObserver()
        setUpRvMobilesGrid()
        loadMobiles()
    }

    private fun loadingMobilesObserver() {
        loadingMobiles.observe(this, {
            if (it) {
                binding?.layoutShimmer?.shimmerViewContainer?.visible()
            } else {
                binding?.layoutShimmer?.shimmerViewContainer?.gone()
            }
        })
    }

    private fun setUpRvMobilesGrid() {
        phonesGridRecyclerAdapter = PhonesGridRecyclerAdapter(requireContext())
        binding?.recyclerView?.adapter = phonesGridRecyclerAdapter
        binding?.recyclerView.setOnItemClickListener(this)
        Paginate.with(binding?.recyclerView, object : Paginate.Callbacks {
            override fun onLoadMore() {
                if (loadingMobiles.value == false && phonesGridRecyclerAdapter.itemCount > 0 && !isMobilesFinished) {
                    loadMobiles()
                }
            }

            override fun isLoading(): Boolean {
                return loadingMobiles.value ?: false
            }

            override fun hasLoadedAllItems(): Boolean {
                return isMobilesFinished
            }

        })
                .setLoadingTriggerThreshold(1)
                .addLoadingListItem(false)
                .build()
    }

    private fun loadMobiles() {
        viewModel.getMobiles(phonesGridRecyclerAdapter.itemCount, viewModel.storeToView?.id)
                .observe(this, mobilesObserver())
    }

    private fun mobilesObserver(): CustomObserverResponse<List<MobilesItem>> {
        return CustomObserverResponse(
                requireActivity(),
                object : CustomObserverResponse.APICallBack<List<MobilesItem>> {
                    override fun onSuccess(
                            statusCode: Int,
                            subErrorCode: ResponseSubErrorsCodeEnum,
                            data: List<MobilesItem>?
                    ) {
                        data?.let {
                            phonesGridRecyclerAdapter.submitItems(it)
                        }
                        if (data.isNullOrEmpty())
                            isMobilesFinished = true
                        loadingMobiles.postValue(false)
                    }

                    override fun onError(
                            subErrorCode: ResponseSubErrorsCodeEnum,
                            message: String,
                            errors: List<GeneralError>?
                    ) {
                        super.onError(subErrorCode, message, errors)
                        loadingMobiles.postValue(false)
                        hideShowNoData()
                    }

                    override fun onLoading() {
                        loadingMobiles.postValue(true)
                    }
                }, false, showError = false
        )
    }

    private fun hideShowNoData() {
//        if (accessoriesRecyclerAdapter.itemCount == 0) {
//            binding?.recyclerView?.gone()
//            binding?.layoutNoData?.linearNoData?.visible()
//        } else {
//            binding?.layoutNoData?.linearNoData?.gone()
//            binding?.recyclerView?.visible()
//        }
    }

    override fun onItemClick(view: View?, position: Int, item: Any) {
        MobileDetailsActivity.start(requireActivity(), item as MobilesItem)
    }


}
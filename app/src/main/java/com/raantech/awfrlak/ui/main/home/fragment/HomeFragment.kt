package com.raantech.awfrlak.ui.main.home.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.raantech.awfrlak.R
import com.raantech.awfrlak.data.api.response.GeneralError
import com.raantech.awfrlak.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.data.common.CustomObserverResponse
import com.raantech.awfrlak.data.models.home.*
import com.raantech.awfrlak.databinding.FragmentHomeBinding
import com.raantech.awfrlak.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.raantech.awfrlak.ui.base.bindingadapters.setOnItemClickListener
import com.raantech.awfrlak.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.ui.main.home.adapters.AccessoriesRecyclerAdapter
import com.raantech.awfrlak.ui.main.home.adapters.PhonesRecyclerAdapter
import com.raantech.awfrlak.ui.main.home.adapters.SliderAdapter
import com.raantech.awfrlak.ui.main.home.adapters.StoresRecyclerAdapter
import com.raantech.awfrlak.ui.main.viewmodels.MainViewModel
import com.raantech.awfrlak.utils.extensions.disableViews
import com.raantech.awfrlak.utils.extensions.enableViews
import com.raantech.awfrlak.utils.extensions.gone
import com.raantech.awfrlak.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val viewModel: MainViewModel by viewModels()
    private val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isFinished = false

    lateinit var accessoriesRecyclerAdapter: AccessoriesRecyclerAdapter
    lateinit var phonesRecyclerAdapter: PhonesRecyclerAdapter
    lateinit var sliderAdapter: SliderAdapter
    lateinit var storesRecyclerAdapter: StoresRecyclerAdapter

    var refreshData: Boolean = false

    override fun onResume() {
        super.onResume()
        if (!refreshData) {
            refreshData = true
            return
        }
        reloadData()
    }

    override fun onViewVisible() {
        super.onViewVisible()
        setUpBinding()
        loadingObserver()
        setUpListeners()
        init()
    }

    private fun setUpBinding() {
        binding?.viewModel = viewModel
    }

    private fun setUpListeners() {
//        binding?.tvAll?.setOnClickListener {
//            viewModel.horseAdsTypeEnum.value = HorseAdsTypeEnum.ALL
//            reloadData()
//        }
//        binding?.tvMazad?.setOnClickListener {
//            viewModel.horseAdsTypeEnum.value = HorseAdsTypeEnum.AUCTION
//            reloadData()
//        }
//        binding?.tvSell?.setOnClickListener {
//            viewModel.horseAdsTypeEnum.value = HorseAdsTypeEnum.SELL
//            reloadData()
//        }
    }

    private fun init() {
        setUpRvMobiles()
        setUpRvAccessories()
        setUpRvStores()
        setUpRvSlider()
        loadData()
    }

    private fun loadingObserver() {
        loading.observe(this, Observer {
            if (it) {
                binding?.layoutSlider?.layoutShimmer?.shimmerViewContainer?.visible()
                binding?.layoutPhones?.layoutShimmer?.shimmerViewContainer?.visible()
                binding?.layoutAccessories?.layoutShimmer?.shimmerViewContainer?.visible()
                binding?.layoutStores?.layoutShimmer?.shimmerViewContainer?.visible()

//                binding?.layoutShimmer?.shimmerViewContainer?.startShimmer()
            } else {
                binding?.layoutSlider?.layoutShimmer?.shimmerViewContainer?.gone()
                binding?.layoutPhones?.layoutShimmer?.shimmerViewContainer?.gone()
                binding?.layoutAccessories?.layoutShimmer?.shimmerViewContainer?.gone()
                binding?.layoutStores?.layoutShimmer?.shimmerViewContainer?.gone()

//                binding?.layoutShimmer?.shimmerViewContainer?.stopShimmer()
            }
        })
    }

    private fun reloadData() {
        phonesRecyclerAdapter.clear()
        accessoriesRecyclerAdapter.clear()
        storesRecyclerAdapter.clear()
        sliderAdapter.clear()
        loadData()
    }

    private fun loadData() {
        viewModel.getHome().observe(this, homeObserver())
    }

    private fun setUpRvAccessories() {
        accessoriesRecyclerAdapter = AccessoriesRecyclerAdapter(requireContext())
        binding?.layoutAccessories?.recyclerView?.adapter = accessoriesRecyclerAdapter
        binding?.layoutAccessories?.recyclerView.setOnItemClickListener(this)
    }

    private fun setUpRvMobiles() {
        phonesRecyclerAdapter = PhonesRecyclerAdapter(requireContext())
        binding?.layoutPhones?.recyclerView?.adapter = phonesRecyclerAdapter
        binding?.layoutPhones?.recyclerView.setOnItemClickListener(this)
    }

    private fun setUpRvStores() {
        storesRecyclerAdapter = StoresRecyclerAdapter(requireContext())
        binding?.layoutStores?.recyclerView?.adapter = storesRecyclerAdapter
        binding?.layoutStores?.recyclerView.setOnItemClickListener(this)
    }

    private fun setUpRvSlider() {
        sliderAdapter = SliderAdapter(requireContext())
        binding?.layoutSlider?.recyclerView?.adapter = sliderAdapter
        binding?.layoutSlider?.recyclerView.setOnItemClickListener(this)
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


    private fun homeObserver(): CustomObserverResponse<HomeResponse> {
        return CustomObserverResponse(
            requireActivity(),
            object : CustomObserverResponse.APICallBack<HomeResponse> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: HomeResponse?
                ) {
                    data?.let {
                        data.slider?.let {
                            it.slides?.let { it1 -> sliderAdapter.submitItems(it1) }
                        }
                        data.accessories?.let {
                            accessoriesRecyclerAdapter.submitItems(it)
                        }
                        data.mobiles?.let {
                            phonesRecyclerAdapter.submitItems(it)
                        }
                        data.stores?.let {
                            storesRecyclerAdapter.submitItems(it)
                        }
                    }
                    loading.postValue(false)
                }

                override fun onError(
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    message: String,
                    errors: List<GeneralError>?
                ) {
                    super.onError(subErrorCode, message, errors)
                    loading.postValue(false)
                    hideShowNoData()
                }

                override fun onLoading() {
                    loading.postValue(true)
                }
            }, false, showError = false
        )
    }

    override fun onItemClick(view: View?, position: Int, item: Any) {
        when (item) {
            is MobilesItem -> {

            }
            is AccessoriesItem -> {

            }
            is Store -> {

            }
            is SlidesItem -> {

            }
        }
    }

}
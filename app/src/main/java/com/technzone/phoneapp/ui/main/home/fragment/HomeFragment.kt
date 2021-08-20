package com.technzone.phoneapp.ui.main.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.paginate.Paginate
import com.paginate.recycler.LoadingListItemCreator
import com.technzone.phoneapp.R
import com.technzone.phoneapp.data.api.response.GeneralError
import com.technzone.phoneapp.data.api.response.ResponseSubErrorsCodeEnum
import com.technzone.phoneapp.data.api.response.ResponseWrapper
import com.technzone.phoneapp.data.common.CustomObserverResponse
import com.technzone.phoneapp.data.enums.ServicesType
import com.technzone.phoneapp.data.models.accessories.Accessory
import com.technzone.phoneapp.databinding.FragmentHomeBinding
import com.technzone.phoneapp.ui.base.adapters.BaseBindingRecyclerViewAdapter
import com.technzone.phoneapp.ui.base.bindingadapters.setOnItemClickListener
import com.technzone.phoneapp.ui.base.fragment.BaseBindingFragment
import com.technzone.phoneapp.ui.main.home.adapters.AccessoriesRecyclerAdapter
import com.technzone.phoneapp.ui.main.viewmodels.MainViewModel
import com.technzone.phoneapp.utils.extensions.disableViews
import com.technzone.phoneapp.utils.extensions.enableViews
import com.technzone.phoneapp.utils.extensions.gone
import com.technzone.phoneapp.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(),
    BaseBindingRecyclerViewAdapter.OnItemClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private val viewModel: MainViewModel by viewModels()
    private val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isFinished = false

    lateinit var accessoriesRecyclerAdapter: AccessoriesRecyclerAdapter

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
        setUpRecyclerView()
        loadData()
    }

    private fun loadingObserver() {
        loading.observe(this, Observer {
            if (it) {
                binding?.root?.disableViews()
            } else {
                binding?.root?.enableViews()
            }
        })
    }

    private fun reloadData() {
        accessoriesRecyclerAdapter.clear()
        loadData()
    }

    private fun loadData() {
        viewModel.getAccessories(accessoriesRecyclerAdapter.itemCount,ServicesType.ACCESSORY.value).observe(this, horsesObserver())
    }

    private fun setUpRecyclerView() {
        accessoriesRecyclerAdapter = AccessoriesRecyclerAdapter(requireContext())
        binding?.recyclerView?.adapter = accessoriesRecyclerAdapter
        binding?.recyclerView.setOnItemClickListener(this)
        Paginate.with(binding?.recyclerView, object : Paginate.Callbacks {
            override fun onLoadMore() {
                if (loading.value == false && accessoriesRecyclerAdapter.itemCount > 0 && !isFinished) {
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
            .addLoadingListItem(true)
            .setLoadingListItemCreator(object : LoadingListItemCreator {
                override fun onCreateViewHolder(
                    parent: ViewGroup?,
                    viewType: Int
                ): RecyclerView.ViewHolder {
                    val view = LayoutInflater.from(parent!!.context)
                        .inflate(R.layout.loading_row, parent, false)
                    return object : RecyclerView.ViewHolder(view) {}
                }

                override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

                }

            })
            .build()
    }

    private fun hideShowNoData() {
        if (accessoriesRecyclerAdapter.itemCount == 0) {
            binding?.recyclerView?.gone()
            binding?.layoutNoData?.linearNoData?.visible()
        } else {
            binding?.layoutNoData?.linearNoData?.gone()
            binding?.recyclerView?.visible()
        }
    }


    private fun horsesObserver(): CustomObserverResponse<List<Accessory>> {
        return CustomObserverResponse(
            requireActivity(),
            object : CustomObserverResponse.APICallBack<List<Accessory>> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: ResponseWrapper<List<Accessory>>?
                ) {
                    isFinished = data?.body?.isNullOrEmpty() == true
                    data?.body?.let {
                        accessoriesRecyclerAdapter.addItems(it)
                    }
                    loading.postValue(false)
                    hideShowNoData()
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
        item as Accessory
//        item.id?.let { HorseActivity.start(requireContext(), it) }
    }

}
package com.raantech.awfrlak.ui.store

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.raantech.awfrlak.R
import com.raantech.awfrlak.data.common.Constants
import com.raantech.awfrlak.data.models.home.Store
import com.raantech.awfrlak.databinding.ActivityStoreBinding
import com.raantech.awfrlak.ui.base.activity.BaseBindingActivity
import com.raantech.awfrlak.ui.main.viewmodels.MainViewModel
import com.raantech.awfrlak.ui.store.fragment.AccessoriesFragment
import com.raantech.awfrlak.ui.store.fragment.MobilesFragment
import com.raantech.awfrlak.ui.store.fragment.ServicesFragment
import com.raantech.awfrlak.utils.extensions.getStatusBarHeight
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_store.*


@AndroidEntryPoint
class StoreActivity : BaseBindingActivity<ActivityStoreBinding>() {

    val viewModel: MainViewModel by viewModels()
    private val mFragmentList = mutableListOf<Fragment>()

    companion object {
        fun start(
                context: Activity,
                item: Store,
//                profileImage: View,
//                profileName: View,
//                profileRate: View,
//                profilePriceRange: View
        ) {

//            val p1: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(
//                    profileImage,
//                    profileImage.transitionName
//            )
//            val p2: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(
//                    profileName,
//                    profileName.transitionName
//            )
//            val p3: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(
//                    profileRate,
//                    profileRate.transitionName
//            )
//            val p4: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(
//                    profilePriceRange,
//                    profilePriceRange.transitionName
//            )
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    context,
//                    p1,
//                    p2,
//                    p3,
//                    p4
//            )
            val intent = Intent(context, StoreActivity::class.java)
            intent.putExtra(Constants.BundleData.STORE, item)
            context.startActivity(intent /*options.toBundle()*/)
        }

        fun start(
                context: Activity,
                storeId: String
        ) {
            val intent = Intent(context, StoreActivity::class.java)
            intent.putExtra(Constants.BundleData.STORE_ID, storeId)
            context.startActivity(intent)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.storeToView = intent.getSerializableExtra(Constants.BundleData.STORE) as Store?
        setContentView(
                R.layout.activity_store,
                hasToolbar = true,
                toolbarView = binding?.toolbar?.toolbar,
                hasBackButton = true,
                showBackArrow = true,
                hasTitle = true,
                titleString = viewModel.storeToView?.name
        )
//        binding?.viewModel = viewModel
        coordinator.setPadding(0, getStatusBarHeight() * -1, 0, 0)
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
        initData()
        setUpListeners()
    }

    private fun initData() {
//        viewModel.store =
//                intent.getSerializableExtra(Constants.BundleData.STORE) as Store
//        binding?.layoutSpecialistInfo?.data = viewModel.store
        setUpTabLayout()
    }


    private fun setUpListeners() {

    }

    fun setUpTabLayout() {
        setUpViewPager()
        tab_layout.addTab(tab_layout.newTab(), 0, true)
        tab_layout.addTab(tab_layout.newTab(), 1, false)
        tab_layout.addTab(tab_layout.newTab(), 2, false)
        tab_layout.setSelectedTabIndicatorColor(Color.parseColor("#00000000"))
        tab_layout.setSelectedTabIndicatorHeight(0)
        tab_layout.setTabTextColors(
                Color.parseColor("#000000"),
                Color.parseColor("#ffffff")
        )
        tab_layout.getTabAt(0)?.text = resources.getString(R.string.mobiles)
        tab_layout.getTabAt(1)?.text = resources.getString(R.string.accessories)
        tab_layout.getTabAt(2)?.text = resources.getString(R.string.services)

        tab_layout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }

    private fun setUpViewPager() {
        mFragmentList.add(MobilesFragment())
        mFragmentList.add(AccessoriesFragment())
        mFragmentList.add(ServicesFragment())
//        mFragmentList.add(SpecialistRatingFragment())
//
        val adapter: FragmentStatePagerAdapter =
                object : FragmentStatePagerAdapter(supportFragmentManager) {
                    override fun getItem(position: Int): Fragment {
                        return mFragmentList.get(position)
                    }

                    override fun getCount(): Int {
                        return mFragmentList.size
                    }
                }
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 5
    }

}
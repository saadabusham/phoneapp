package com.raantech.awfrlak.user.ui.orders.activtiy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.raantech.awfrlak.R
import com.raantech.awfrlak.user.data.pref.user.UserPref
import com.raantech.awfrlak.databinding.ActivityAuthBinding
import com.raantech.awfrlak.databinding.ActivityOrdersBinding
import com.raantech.awfrlak.user.ui.base.activity.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.auth_nav_host_fragment
import kotlinx.android.synthetic.main.activity_orders.*
import javax.inject.Inject

@AndroidEntryPoint
class OrdersActivity : BaseBindingActivity<ActivityOrdersBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders, hasToolbar = false)

        setStartDestination()
    }

    private fun setStartDestination() {

        val navHostFragment = orders_nav_host_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.orders_nav_graph)
        navHostFragment.navController.graph = graph
    }

    companion object {
        fun start(
            context: Context?
        ) {
            val intent = Intent(context, OrdersActivity::class.java)
            context?.startActivity(intent)
        }
    }

}
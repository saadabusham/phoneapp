package com.raantech.awfrlak.user.ui.more.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.raantech.awfrlak.R
import com.raantech.awfrlak.user.common.CommonEnums
import com.raantech.awfrlak.databinding.ActivitySettingsBinding
import com.raantech.awfrlak.user.ui.base.activity.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.layout_toolbar.*


@AndroidEntryPoint
class SettingsActivity : BaseBindingActivity<ActivitySettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                layoutResID = R.layout.activity_settings,
                hasToolbar = true,
                toolbarView = toolbar,
                hasBackButton = true,
                showBackArrow = true,
                hasTitle = true,
                titleString = resources.getString(R.string.settings)

        )
        binding?.viewModel = viewModel
        setUpListeners()
    }

    private fun setUpListeners() {
        switchNotifications?.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setIsNotificationsIsEnabled(isChecked)
        }
//        binding?.switchId?.setOnCheckedChangeListener { buttonView, isChecked ->
//            viewModel.setIsTouchIDEnabled(isChecked)
//        }
        linearLanguage?.setOnClickListener {
            viewModel.saveLanguage().observe(this, Observer {
                (this as BaseBindingActivity<*>)
                        .setLanguage(if (viewModel.englishSelected.value!!)
                            CommonEnums.Languages.English.value
                        else CommonEnums.Languages.Arabic.value)
            })
        }
    }

    companion object {

        fun start(context: Context?) {
            val intent = Intent(context, SettingsActivity::class.java)
            context?.startActivity(intent)
        }

    }

}
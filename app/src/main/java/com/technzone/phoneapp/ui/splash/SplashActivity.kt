package com.technzone.phoneapp.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.technzone.phoneapp.R
import com.technzone.phoneapp.common.MyApplication
import com.technzone.phoneapp.data.api.response.ResponseSubErrorsCodeEnum
import com.technzone.phoneapp.data.common.CustomObserverResponse
import com.technzone.phoneapp.data.models.configuration.ConfigurationWrapperResponse
import com.technzone.phoneapp.databinding.ActivitySplashBinding
import com.technzone.phoneapp.ui.auth.AuthActivity
import com.technzone.phoneapp.ui.base.activity.BaseBindingActivity
import com.technzone.phoneapp.ui.main.MainActivity
import com.technzone.phoneapp.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {

    private val viewModel: SplashViewModel by viewModels { defaultViewModelProviderFactory }

    @Inject
    lateinit var myApp: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            layoutResID = R.layout.activity_splash,
            hasToolbar = false
        )
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getConfigurationData().observe(this, configurationResultObserver())
        }, 3000)

        RuntimeException("This is a RUNTIME EXCEPTION")
    }

    private fun configurationResultObserver(): CustomObserverResponse<ConfigurationWrapperResponse> {
        return CustomObserverResponse(
            this,
            object : CustomObserverResponse.APICallBack<ConfigurationWrapperResponse> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: ConfigurationWrapperResponse?
                ) {
                    when {
                        subErrorCode == ResponseSubErrorsCodeEnum.Success -> {
                            SharedPreferencesUtil.getInstance(this@SplashActivity)
                                .setConfigurationPreferences(data)
                            goToNextPage()
                        }
                    }
                }
            })
    }
    private fun goToNextPage() {
        if (!viewModel.isUserLoggedIn()) {
            AuthActivity.start(this)
        } else
            MainActivity.start(this)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onNewIntent(intent: Intent?) {
        this.intent = intent
        super.onNewIntent(intent)
    }


    companion object {

        fun start(context: Context?) {
            val intent = Intent(context, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }

    }

}
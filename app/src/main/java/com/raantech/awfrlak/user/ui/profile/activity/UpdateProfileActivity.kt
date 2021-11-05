package com.raantech.awfrlak.user.ui.profile.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.raantech.awfrlak.R
import com.raantech.awfrlak.databinding.ActivityUpdateProfileBinding
import com.raantech.awfrlak.user.data.api.response.GeneralError
import com.raantech.awfrlak.user.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.user.data.common.CustomObserverResponse
import com.raantech.awfrlak.user.data.models.auth.login.UserDetailsResponseModel
import com.raantech.awfrlak.user.ui.base.activity.BaseBindingActivity
import com.raantech.awfrlak.user.ui.main.MainActivity
import com.raantech.awfrlak.user.ui.profile.viewmodels.UpdateProfileViewModel
import com.raantech.awfrlak.user.utils.extensions.showErrorAlert
import com.raantech.awfrlak.user.utils.extensions.showValidationErrorAlert
import com.raantech.awfrlak.user.utils.extensions.validate
import com.raantech.awfrlak.user.utils.validation.ValidatorInputTypesEnums
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class UpdateProfileActivity : BaseBindingActivity<ActivityUpdateProfileBinding>() {

    private val viewModel : UpdateProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_update_profile,
            hasToolbar = true,
            toolbarView = toolbar,
            hasBackButton = true,
            showBackArrow = true,
            hasTitle = true,
            title = R.string.menu_account
        )
        binding?.viewModel = viewModel
        setUpListeners()
    }

    private fun setUpListeners() {
        binding?.btnRegister?.setOnClickListener {
            if (validateInput())
                viewModel.updateUser().observe(this, updateResultObserver())
        }
    }

    private fun validateInput(): Boolean {
        binding?.edUserName?.text.toString().validate(
            ValidatorInputTypesEnums.TEXT,
            this
        ).let {
                if (!it.isValid) {
                    showValidationErrorAlert(
                        title = getString(R.string.username),
                        message = it.errorMessage
                    )
                    return false
                }
            }
        binding?.edPhoneNumber?.text.toString().validate(
            ValidatorInputTypesEnums.PHONE_NUMBER,
            this
        ).let {
                if (!it.isValid) {
                    showValidationErrorAlert(
                        title = getString(R.string.username),
                        message = it.errorMessage
                    )
                    return false
                }
            }
        binding?.edEmail?.text.toString().validate(
            ValidatorInputTypesEnums.EMAIL,
            this
        ).let {
                if (!it.isValid) {
                    showValidationErrorAlert(
                        title = getString(R.string.email),
                        message = it.errorMessage
                    )
                    return false
                }
            }
        binding?.tvAddress?.text.toString().validate(
            ValidatorInputTypesEnums.TEXT,
            this
        ).let {
                if (!it.isValid) {
                    showValidationErrorAlert(
                        title = getString(R.string.address),
                        message = it.errorMessage
                    )
                    return false
                }
            }

        return true
    }

    private fun updateResultObserver(): CustomObserverResponse<UserDetailsResponseModel> {
        return CustomObserverResponse(
            this,
            object : CustomObserverResponse.APICallBack<UserDetailsResponseModel> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: UserDetailsResponseModel?
                ) {
                    data?.let {
                        if (data.is_registered == true) {
                            viewModel.storeUser(it)
                            MainActivity.start(this@UpdateProfileActivity)
                        }
                    }
                }

                override fun onError(
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    message: String,
                    errors: List<GeneralError>?
                ) {
                    super.onError(subErrorCode, message, errors)
                    errors?.get(0)?.let {
                        showErrorAlert(it.key, it.getErrorsString())
                    }
                }
            }, showError = false
        )
    }

    companion object {
        fun start(
            context: Context?
        ) {
            val intent = Intent(context, UpdateProfileActivity::class.java)
            context?.startActivity(intent)
        }
    }
}
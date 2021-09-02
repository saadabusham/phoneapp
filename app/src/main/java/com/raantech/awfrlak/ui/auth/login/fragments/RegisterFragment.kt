package com.raantech.awfrlak.ui.auth.login.fragments

import androidx.navigation.navGraphViewModels
import com.raantech.awfrlak.R
import com.raantech.awfrlak.data.api.response.GeneralError
import com.raantech.awfrlak.data.api.response.ResponseSubErrorsCodeEnum
import com.raantech.awfrlak.data.common.CustomObserverResponse
import com.raantech.awfrlak.data.models.auth.login.UserDetailsResponseModel
import com.raantech.awfrlak.databinding.FragmentRegisterBinding
import com.raantech.awfrlak.ui.auth.login.viewmodels.LoginViewModel
import com.raantech.awfrlak.ui.base.fragment.BaseBindingFragment
import com.raantech.awfrlak.ui.main.MainActivity
import com.raantech.awfrlak.utils.extensions.showErrorAlert
import com.raantech.awfrlak.utils.extensions.showValidationErrorAlert
import com.raantech.awfrlak.utils.extensions.validate
import com.raantech.awfrlak.utils.validation.ValidatorInputTypesEnums
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class RegisterFragment : BaseBindingFragment<FragmentRegisterBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_register

    private val viewModel: LoginViewModel by navGraphViewModels(R.id.auth_nav_graph) { defaultViewModelProviderFactory }

    override fun onViewVisible() {
        super.onViewVisible()
        addToolbar(
                hasToolbar = true,
                toolbarView = toolbar,
                hasBackButton = true,
                showBackArrow = true,
                hasTitle = true,
                title = R.string.register
        )
        binding?.viewModel = viewModel
        setUpListeners()
    }

    private fun setUpListeners() {
        binding?.btnRegister?.setOnClickListener {
            if (validateInput())
                viewModel.registerUser().observe(this, registerResultObserver())
        }
    }

    private fun validateInput(): Boolean {
        binding?.edUserName?.text.toString().validate(
                ValidatorInputTypesEnums.TEXT,
                requireContext()
        )
                .let {
                    if (!it.isValid) {
                        requireActivity().showValidationErrorAlert(
                                title = getString(R.string.username),
                                message = it.errorMessage
                        )
                        return false
                    }
                }
        binding?.edEmail?.text.toString().validate(
                ValidatorInputTypesEnums.EMAIL,
                requireContext()
        )
                .let {
                    if (!it.isValid) {
                        requireActivity().showValidationErrorAlert(
                                title = getString(R.string.email),
                                message = it.errorMessage
                        )
                        return false
                    }
                }
        binding?.tvAddress?.text.toString().validate(
                ValidatorInputTypesEnums.TEXT,
                requireContext()
        )
                .let {
                    if (!it.isValid) {
                        requireActivity().showValidationErrorAlert(
                                title = getString(R.string.address),
                                message = it.errorMessage
                        )
                        return false
                    }
                }

        return true
    }

    private fun registerResultObserver(): CustomObserverResponse<UserDetailsResponseModel> {
        return CustomObserverResponse(
                requireActivity(),
                object : CustomObserverResponse.APICallBack<UserDetailsResponseModel> {
                    override fun onSuccess(
                            statusCode: Int,
                            subErrorCode: ResponseSubErrorsCodeEnum,
                            data: UserDetailsResponseModel?
                    ) {
                        data?.let {
                            if (data.is_registered == true) {
                                viewModel.storeUser(it)
                                MainActivity.start(requireContext())
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
                            requireActivity().showErrorAlert(it.key, it.getErrorsString())
                        }
                    }
                }, showError = false
        )
    }

}
package com.technzone.phoneapp.ui.auth.login.fragments

import androidx.navigation.navGraphViewModels
import androidx.viewpager2.widget.ViewPager2
import com.technzone.phoneapp.ui.auth.login.viewmodels.LoginViewModel
import com.technzone.phoneapp.R
import com.technzone.phoneapp.data.api.response.GeneralError
import com.technzone.phoneapp.data.api.response.ResponseSubErrorsCodeEnum
import com.technzone.phoneapp.data.common.CustomObserverResponse
import com.technzone.phoneapp.data.models.auth.login.TokenModel
import com.technzone.phoneapp.data.models.auth.onboarding.OnBoardingItem
import com.technzone.phoneapp.data.pref.user.UserPref
import com.technzone.phoneapp.databinding.FragmentLoginBinding
import com.technzone.phoneapp.ui.auth.login.adapters.IndecatorRecyclerAdapter
import com.technzone.phoneapp.ui.auth.login.adapters.OnBoardingAdapter
import com.technzone.phoneapp.ui.base.fragment.BaseBindingFragment
import com.technzone.phoneapp.utils.extensions.showErrorAlert
import com.technzone.phoneapp.utils.extensions.showValidationErrorAlert
import com.technzone.phoneapp.utils.extensions.validate
import com.technzone.phoneapp.utils.validation.ValidatorInputTypesEnums
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_login
    lateinit var indicatorRecyclerAdapter: IndecatorRecyclerAdapter
    lateinit var onBoardingAdapter: OnBoardingAdapter
    private var indicatorPosition = 0

    @Inject
    lateinit var prefs: UserPref
    private val viewModel: LoginViewModel by navGraphViewModels(R.id.auth_nav_graph) { defaultViewModelProviderFactory }

    override fun onViewVisible() {
        super.onViewVisible()
        binding?.viewModel = viewModel
        setUpListeners()
        setUpPager()
    }

    private fun setUpIndicator() {
        indicatorRecyclerAdapter = IndecatorRecyclerAdapter(requireContext())
        binding?.recyclerViewImagesDot?.adapter = indicatorRecyclerAdapter
        onBoardingAdapter.items.let {
            it.withIndex().forEach {
                indicatorRecyclerAdapter.submitItem(it.index == 0)
            }
        }
        binding?.vpOnBoarding?.registerOnPageChangeCallback(
            pagerCallback
        )
    }

    private fun setUpPager() {

        val items = arrayOf(
            OnBoardingItem(
                title = R.string.title_on_boarding_1
            ),
            OnBoardingItem(
                title = R.string.title_on_boarding_2
            ),
            OnBoardingItem(
                title = R.string.title_on_boarding_3
            ),
            OnBoardingItem(
                title = R.string.title_on_boarding_4
            )
        )
        onBoardingAdapter = OnBoardingAdapter(requireContext())
        binding?.vpOnBoarding?.adapter =
            onBoardingAdapter.apply { submitItems(items.toList()) }
        setUpIndicator()
    }


    private var pagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            updateIndicator(position)
        }
    }

    private fun updateIndicator(position: Int) {
        indicatorRecyclerAdapter.items[indicatorPosition] = false
        indicatorRecyclerAdapter.items[position] = true
        indicatorRecyclerAdapter.notifyDataSetChanged()
        indicatorPosition = position
    }

    private fun setUpListeners() {
        viewModel.selectedCountryCode.postValue(binding?.countryCodePicker?.defaultCountryCode)
        binding?.countryCodePicker?.setOnCountryChangeListener {
            viewModel.selectedCountryCode.postValue(it.phoneCode)
        }
        binding?.btnLogin?.setOnClickListener {
            if (validateInput())
                viewModel.loginUser().observe(this, loginResultObserver())
        }
    }

    private fun validateInput(): Boolean {
        binding?.etPhoneNumber?.text.toString().validate(
            ValidatorInputTypesEnums.PHONE_NUMBER,
            requireContext()
        )
            .let {
                if (!it.isValid) {
                    requireActivity().showValidationErrorAlert(
                        title = it.errorTitle,
                        message = it.errorMessage
                    )
                    return false
                }
            }
        return true
    }

    private fun loginResultObserver(): CustomObserverResponse<TokenModel> {
        return CustomObserverResponse(
            requireActivity(),
            object : CustomObserverResponse.APICallBack<TokenModel> {
                override fun onSuccess(
                    statusCode: Int,
                    subErrorCode: ResponseSubErrorsCodeEnum,
                    data: TokenModel?
                ) {
                    data?.let {
                        viewModel.userTokenMutableLiveData.postValue(it.token)
                        navigationController.navigate(R.id.action_loginFragment_to_verificationLoginFragment)
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
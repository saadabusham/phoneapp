package com.raantech.awfrlak.user.ui.payment.viewmodels

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raantech.awfrlak.user.ui.base.viewmodel.BaseViewModel
import com.raantech.awfrlak.user.utils.pref.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.ExecutionException
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val sharedPreferences: SharedPreferencesUtil
) : BaseViewModel() {

}
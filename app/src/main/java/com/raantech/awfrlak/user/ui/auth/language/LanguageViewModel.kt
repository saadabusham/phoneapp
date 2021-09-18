package com.raantech.awfrlak.user.ui.auth.language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.raantech.awfrlak.user.common.CommonEnums
import com.raantech.awfrlak.user.data.pref.configuration.ConfigurationPref
import com.raantech.awfrlak.user.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val configurationpref: ConfigurationPref
) : BaseViewModel() {

    val englishSelected: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(true) }
    fun onEnglishClicked() {
        englishSelected.postValue(true)
    }

    fun onArabicClicked() {
        englishSelected.postValue(false)
    }

    fun saveLanguage() = liveData {
        configurationpref.setAppLanguageValue(if (englishSelected.value!!) CommonEnums.Languages.English.value else CommonEnums.Languages.Arabic.value)
        emit(null)
    }

}
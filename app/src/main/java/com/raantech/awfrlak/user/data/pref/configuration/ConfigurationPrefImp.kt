package com.raantech.awfrlak.user.data.pref.configuration

import com.raantech.awfrlak.user.common.CommonEnums
import com.raantech.awfrlak.user.utils.pref.PrefConstants.APP_LANGUAGE_VALUE
import com.raantech.awfrlak.user.utils.pref.SharedPreferencesUtil
import javax.inject.Inject

class ConfigurationPrefImp @Inject constructor(private val prefUtil: SharedPreferencesUtil) :
    ConfigurationPref {

    override fun setAppLanguageValue(selectedLanguageValue: String) {
        prefUtil.setStringPreferences(APP_LANGUAGE_VALUE, selectedLanguageValue)
    }

    override fun getAppLanguageValue(): String {
        return prefUtil.getStringPreferences(
            APP_LANGUAGE_VALUE,
           CommonEnums.Languages.English.value
        )
    }
}